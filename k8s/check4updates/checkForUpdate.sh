#!/bin/bash
host="dauer23"
image_name="chrispbaconend"
tag="latest"
backend_image="$host/$image_name:$tag"
deployment_name="chris-p-baconend"

if ! minikube status | grep -q "Running"; then
    echo "Minikube is not running. Please start Minikube and try again."
    exit 1
fi

if ! minikube ssh docker inspect "$backend_image" &> /dev/null; then
    echo "The image $backend_image does not exist locally. Make sure to apply the '$deployment_name' deployment before running this script."
    exit 1
fi

localImageTimestamp=$(minikube ssh docker inspect "$backend_image" | grep -Eo '"Created": "[^"]+"' | cut -d '"' -f4)
echo "Last update of the local docker image: $localImageTimestamp"


response=$(curl -m 10 -s -X GET "https://hub.docker.com/v2/repositories/$host/$image_name/tags/$tag")

if [ $? -ne 0 ]; then
    echo "Error: Failed to retrieve data from Docker Hub."
    exit 1
fi

if echo "$response" | grep -q '"last_updated"'; then
    remoteImageTimestamp=$(echo "$response" | grep -oP '"last_updated":"\K[^",]+')
    echo "Last update of the remote docker image: $remoteImageTimestamp"
else
    echo "Error: Unable to retrieve image information from Docker Hub."
    exit 1
fi

./TimestampComparision "$localImageTimestamp" "$remoteImageTimestamp"

if [ $? -eq 100 ]; then
    echo "New version detected! Taking action..."
    kubectl rollout restart deployment/"$deployment_name"
    echo "Thank you for using the script. Bye."
else
    echo "The local image is up to date. Stay frosty and off the pork. Pigs are your friends <3."
fi
