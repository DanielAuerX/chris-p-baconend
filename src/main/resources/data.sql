insert into learning_field (id, name) values (1, 'Das Unternehmen und die eigene Rolle im Betrieb beschreiben');
insert into learning_field (id, name) values (2, 'Arbeitsplätze nach Kund:innenwunsch ausstatten');
insert into learning_field (id, name) values (3, 'Clients in Netzwerke einbinden');
insert into learning_field (id, name) values (4, 'Schutzbedarfsanalyse im eigenen Arbeitsbereich durchführen');

insert into category (id, learning_field_id, name, description, text) values (20, 2, 'Energiebedarf und Energiekosten berechnen', 'Kenne den Energiebedarf um dein System effizient zu nutzen', 'In diesem Lernfeld "Arbeitsplätze nach Kund:innenwunsch ausstatten" in der Fachinformatiker-Ausbildung werden wir uns auf die Berechnung des Energiebedarfs und die Energiekosten konzentrieren. Wir werden lernen, wie wir den Energieverbrauch von IT-Systemen analysieren und optimieren können, um effiziente und kostengünstige Arbeitsplätze zu gestalten. Dabei stehen ökologische und ökonomische Aspekte gleichermaßen im Fokus. Wir werden Methoden und Technologien erkunden, um den Energieverbrauch zu überwachen und zu steuern, und die Bedeutung von energieeffizienter Hardware und umweltfreundlichen Lösungen in diesem Kontext untersuchen.\nZusätzliche Resourcen: https://moodle.itech-bs14.de/course/view.php?id=839');
insert into category (id, learning_field_id, name, description, text) values (30, 3, 'Netzwerkplan', 'Lesen und Erstellen von Netzwerkplänen', 'Text, text , text .Zusätzliche Resourcen: https://moodle.itech-bs14.de/pluginfile.php/174227/mod_resource/content/1/Network%20Plans%20Drawing%20Example.pdf');
insert into category (id, learning_field_id, name, description, text) values (31, 3, 'Buy or Lease', 'Buy or Lease... das ist die Frage', 'Text, text , text .Zusätzliche Resourcen: https://google.com');
insert into category (id, learning_field_id, name, description, text) values (32, 3, 'IPv4', 'Eigenschaften von IPv4', 'Text, text , text .Zusätzliche Resourcen: https://google.com');
insert into question (id, category_id, learning_field_id, question) values ('4a3318b5-3a02-400b-9807-730962452fa7', 20, 2, 'Welche ist die korrekte Formel um elektrische Arbeit W zu berechnen?');
insert into question (id, category_id, learning_field_id, question) values ('4a3318b4-3a02-400b-9807-730962452fa6', 20, 2, 'Wann ist der Input?');

-- Learning Field 1: Das Unternehmen und die eigene Rolle im Betrieb beschreiben
insert into category (id, learning_field_id, name, description, text) values (11, 1, 'Unternehmensstruktur verstehen', 'Die Struktur des Unternehmens kennenlernen', '<h3>Unternehmensstrukturen und -typen: Eine Einführung</h3>
  <p>Das Verständnis von Unternehmensstrukturen und -typen ist von grundlegender Bedeutung, um die Arbeitsweise und Organisation von Unternehmen zu durchdringen. In diesem Lernfeld werden die verschiedenen Facetten der Unternehmenswelt beleuchtet, um Ihnen einen Einblick in die Vielfalt der Strukturen und Typen von Unternehmen zu bieten.</p>

  <h3>Unternehmensstrukturen:</h3>
  <p>Unternehmensstrukturen beziehen sich auf die Art und Weise, wie ein Unternehmen intern organisiert ist. Es gibt verschiedene Ansätze, wie Unternehmen ihre internen Hierarchien und Organisationsformen gestalten. Hier sind einige der gängigsten Unternehmensstrukturen:</p>

  <ul>
    <li><strong>Funktionale Struktur:</strong> In dieser Struktur sind Mitarbeiter nach ihren Funktionen oder Aufgabenbereichen organisiert.</li>
    <li><strong>Divisionale Struktur:</strong> Bei dieser Struktur wird das Unternehmen in verschiedene Divisionen oder Geschäftseinheiten unterteilt.</li>
    <li><strong>Matrixstruktur:</strong> Die Matrixstruktur kombiniert Elemente aus beiden Ansätzen.</li>
    <li><strong>Teamstruktur:</strong> Die Teamstruktur betont die Eigenverantwortung von Teams.</li>
  </ul>

  <h3>Unternehmensarten:</h3>
  <p>Neben den Strukturen gibt es verschiedene Unternehmensarten, die sich auf die Rechtsform und Eigentumsverhältnisse beziehen. Hier sind einige Beispiele:</p>

  <ul>
    <li><strong>Einzelunternehmen:</strong> Ein Einzelunternehmen wird von einer einzigen Person geführt und ist rechtlich nicht von seinem Eigentümer getrennt.</li>
    <li><strong>Personengesellschaften:</strong> Hier teilen sich mehrere Personen die Eigentümerschaft und die Verantwortung für das Unternehmen.</li>
    <li><strong>Kapitalgesellschaften:</strong> Kapitalgesellschaften sind rechtlich von ihren Eigentümern getrennt.</li>
  </ul>

  <p>Das Verständnis dieser Strukturen und Typen von Unternehmen ist entscheidend, da sie die Art und Weise beeinflussen, wie Unternehmen geführt werden, wie Entscheidungen getroffen werden und wie sie im Markt agieren. Durch das Kennenlernen dieser Konzepte können Sie ein tieferes Verständnis für die Geschäftswelt entwickeln und besser in Ihrer Rolle im Unternehmen agieren.</p>
');
insert into category (id, learning_field_id, name, description, text) values (12, 1, 'Unternehmensziele und -werte kennenlernen', 'Die Ziele und Werte des Unternehmens verstehen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (13, 1, 'Die eigene Rolle im Unternehmen definieren', 'Die persönliche Rolle im Unternehmen klären', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (14, 1, 'Arbeitsrechtliche Grundlagen kennenlernen', 'Grundlegende Arbeitsrechtsaspekte verstehen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (15, 1, 'Betriebskultur und Teamarbeit verstehen', 'Die Betriebskultur und die Bedeutung von Teamarbeit', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (16, 1, 'Kommunikation im Unternehmen', 'Effektive Kommunikation im Unternehmensumfeld', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (17, 1, 'Umgang mit Konflikten am Arbeitsplatz', 'Konfliktlösungstechniken und -strategien', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (18, 1, 'Mitarbeitermotivation und -führung', 'Motivation und Führung von Mitarbeiter:innen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (19, 1, 'Arbeits- und Gesundheitsschutz im Betrieb', 'Sicherheit und Gesundheit am Arbeitsplatz', 'Text, text, text.');

-- Learning Field 2: Arbeitsplätze nach Kund:innenwunsch ausstatten
insert into category (id, learning_field_id, name, description, text) values (21, 2, 'Energiebedarf und Energiekosten berechnen', 'Kenne den Energiebedarf um dein System effizient zu nutzen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (22, 2, 'Auswahl von Hardwarekomponenten', 'Die richtigen Hardwarekomponenten für Arbeitsplätze wählen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (23, 2, 'Betriebssysteminstallation und Konfiguration', 'Installation und Konfiguration von Betriebssystemen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (24, 2, 'Netzwerkverbindung einrichten', 'Einrichtung von Netzwerkverbindungen für Arbeitsplätze', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (25, 2, 'Softwareinstallation und -konfiguration', 'Installation und Konfiguration von Software auf Arbeitsplätzen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (26, 2, 'Datensicherung und -wiederherstellung', 'Sicherung und Wiederherstellung von Daten auf Arbeitsplätzen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (27, 2, 'Ergonomische Arbeitsplatzgestaltung', 'Schaffung ergonomischer Arbeitsplätze', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (28, 2, 'Kund:innenanforderungen verstehen', 'Verstehen der Anforderungen der Kund:innen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (29, 2, 'Qualitätskontrolle und -sicherung', 'Sicherung der Qualität der eingerichteten Arbeitsplätze', 'Text, text, text.');

-- Learning Field 3: Clients in Netzwerke einbinden
insert into category (id, learning_field_id, name, description, text) values (33, 3, 'Eigenschaften von IPv4', 'Grundlagen und Eigenschaften von IPv4 verstehen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (34, 3, 'IPv6 und die Zukunft des Internets', 'Die Bedeutung von IPv6 für zukünftige Netzwerke', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (35, 3, 'Subnetting und IP-Adressierung', 'Subnetzwerk-Konzepte und IP-Adressierung verstehen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (36, 3, 'Netzwerktopologien verstehen', 'Verschiedene Netzwerktopologien kennenlernen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (37, 3, 'Netzwerkprotokolle und -dienste', 'Grundlegende Netzwerkprotokolle und -dienste verstehen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (38, 3, 'Routing und Switching', 'Routing und Switching in Netzwerken', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (39, 3, 'Netzwerksicherheit und Firewall-Konfiguration', 'Sicherheit in Netzwerken und Konfiguration von Firewalls', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (40, 3, 'Netzwerkmonitoring und -management', 'Überwachung und Management von Netzwerken', 'Text, text, text.');

-- Learning Field 4: Schutzbedarfsanalyse im eigenen Arbeitsbereich durchführen
insert into category (id, learning_field_id, name, description, text) values (41, 4, 'Sensible Daten identifizieren und klassifizieren', 'Identifikation und Klassifizierung sensibler Daten', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (42, 4, 'Risikobewertung und -management', 'Bewertung und Management von Risiken', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (43, 4, 'Sicherheitsrichtlinien und -verfahren', 'Entwicklung und Umsetzung von Sicherheitsrichtlinien und -verfahren', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (44, 4, 'Physische Sicherheit am Arbeitsplatz', 'Sicherheitsmaßnahmen für physische Arbeitsplätze', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (45, 4, 'Zugriffskontrollen und Authentifizierung', 'Verwaltung von Zugriffskontrollen und Authentifizierungssystemen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (46, 4, 'Verschlüsselungstechnologien und -verfahren', 'Verschlüsselungstechnologien und deren Anwendung', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (47, 4, 'Bedrohungs- und Schwachstellenanalyse', 'Analyse von Bedrohungen und Schwachstellen', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (48, 4, 'Incident Response und Notfallplanung', 'Reaktion auf Sicherheitsvorfälle und Notfallplanung', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (49, 4, 'Datensicherung und -wiederherstellung', 'Sicherung und Wiederherstellung von Daten', 'Text, text, text.');
insert into category (id, learning_field_id, name, description, text) values (50, 4, 'Compliance und Datenschutzvorschriften', 'Einhaltung von Compliance und Datenschutzvorschriften', 'Text, text, text.');


insert into answer (id, question_id, answer, correct) values ('bd3528e7-5b4e-42ce-885a-5ed904184cf3', '4a3318b5-3a02-400b-9807-730962452fa7', 'W = Q * U (Elektrische Arbeit W = Elektrizitätsmenge Q * Elektrische Spannung U)', true);
insert into answer (id, question_id, answer, correct) values ('de22a094-279e-4aa5-988e-c042a7d50cfd', '4a3318b5-3a02-400b-9807-730962452fa7', 'W = U / Q (Elektrische Arbeit W = Elektrische Spannung U / Elektrizitätsmenge Q)', false);
insert into answer (id, question_id, answer, correct) values ('98481cc6-788a-46ae-9f3a-5b127a3d1ad0', '4a3318b5-3a02-400b-9807-730962452fa7', 'W = Q + A (Elektrische Arbeit W = Elektrizitätsmenge Q + Ampere A)', false);
insert into answer (id, question_id, answer, correct) values ('88481cc6-788a-46ae-9f3a-5b127a3d1ad1', '4a3318b4-3a02-400b-9807-730962452fa6', 'Timo', false);
insert into answer (id, question_id, answer, correct) values ('de22a094-788a-46ae-9f3a-5b127a3d1ad1', '4a3318b4-3a02-400b-9807-730962452fa6', 'Toni', false);
insert into answer (id, question_id, answer, correct) values ('de22a094-788a-46ae-9f3a-5b127a3d1ad0', '4a3318b4-3a02-400b-9807-730962452fa6', 'Ezekiel', true);
insert into student (id, email, user_name, password, first_name, last_name, role) values ('98481cc6-788a-46ae-9f3a-5b127a3d1ab1', 'timon@web.de', 'todesTimonOfDeath', 'ilovefrontend', 'Timon', 'Reiswaffel', 0);
insert into student_finished_categories (finished_categories, student_id) values (20, '98481cc6-788a-46ae-9f3a-5b127a3d1ab1');
insert into student_finished_categories (finished_categories, student_id) values (32, '98481cc6-788a-46ae-9f3a-5b127a3d1ab1');

-- QA's for cat 11
insert into question (id, category_id, learning_field_id, question) values
                                                                        ('9b3e5b5d-66a5-4f49-8150-7cbbdf3b652e', 11, 1, 'Was ist das Ziel eines Unternehmens?'),
                                                                        ('0d9fb1bf-3c90-49d7-8e09-90f8aa18e0e9', 11, 1, 'Was versteht man unter Marktforschung?'),
                                                                        ('3e4fda7a-8e97-493e-97e1-5fb26f8a0b4f', 11, 1, 'Was ist Outsourcing?'),
                                                                        ('72c0b5e3-9c8d-47f7-99b5-8c4e0a0e3a0c', 11, 1, 'Was sind die vier Ps des Marketings?'),
                                                                        ('a9a35210-c204-4a74-93dd-529b67c725fc', 11, 1, 'Was ist die SWOT-Analyse?');

insert into answer (id, question_id, answer, correct) values
                                                          ('1f78f1c7-998d-45a2-aeff-d6f4c6939877', '9b3e5b5d-66a5-4f49-8150-7cbbdf3b652e', 'Das Ziel eines Unternehmens ist in der Regel die Gewinnerzielung und langfristiges Wachstum.', true),
                                                          ('b305c7d1-3f2d-4642-b6a7-85077314f3b6', '9b3e5b5d-66a5-4f49-8150-7cbbdf3b652e', 'Das Ziel eines Unternehmens ist die Schaffung von Arbeitsplätzen.', false),
                                                          ('6ea83ec0-e496-40db-8e9b-71c52cb144e9', '9b3e5b5d-66a5-4f49-8150-7cbbdf3b652e', 'Das Ziel eines Unternehmens ist die Steigerung der Kundenzufriedenheit.', false),
                                                          ('85759ab9-5b21-4d1f-a9b6-50c743fa0475', '0d9fb1bf-3c90-49d7-8e09-90f8aa18e0e9', 'Marktforschung ist die systematische Erfassung, Analyse und Interpretation von Informationen über Märkte, Kunden und Wettbewerber.', true),
                                                          ('7f3c2122-8f02-4dfe-841f-cb2c46244bb7', '0d9fb1bf-3c90-49d7-8e09-90f8aa18e0e9', 'Marktforschung ist die Planung von Marketingkampagnen.', false),
                                                          ('4342c5f1-6f3e-4ed7-97e2-c08f543914fe', '0d9fb1bf-3c90-49d7-8e09-90f8aa18e0e9', 'Marktforschung ist die Preisgestaltung von Produkten.', false),
                                                          ('f7b29572-ae4a-4e9a-94b2-596dd110b2a2', '3e4fda7a-8e97-493e-97e1-5fb26f8a0b4f', 'Outsourcing ist die Auslagerung von Unternehmensaufgaben oder -funktionen an externe Dienstleister.', true),
                                                          ('2a0d9fbc-03c9-4cc6-8e66-c84f72d8b315', '3e4fda7a-8e97-493e-97e1-5fb26f8a0b4f', 'Outsourcing ist die interne Umstrukturierung eines Unternehmens.', false),
                                                          ('1e3c596d-33ed-4eeb-94a1-21d87714c350', '3e4fda7a-8e97-493e-97e1-5fb26f8a0b4f', 'Outsourcing ist die Gründung neuer Tochterunternehmen.', false),
                                                          ('4a231c9e-347f-4ff1-9e7d-4b6883f7a953', '72c0b5e3-9c8d-47f7-99b5-8c4e0a0e3a0c', 'Die vier Ps des Marketings sind Produkt, Preis, Promotion und Platz (Distribution).', true),
                                                          ('e0e1a01a-90ea-434e-8b35-6f35e11a201f', '72c0b5e3-9c8d-47f7-99b5-8c4e0a0e3a0c', 'Die vier Ps des Marketings sind Planung, Personal, Produktivität und Prozesse.', false),
                                                          ('4a7b09cc-79c9-40f3-bc5f-9e1f7e302869', '72c0b5e3-9c8d-47f7-99b5-8c4e0a0e3a0c', 'Die vier Ps des Marketings sind Plan, Produktion, Preise und Public Relations.', false),
                                                          ('39255f8f-0b8e-4bf3-8f42-1783446f7e79', 'a9a35210-c204-4a74-93dd-529b67c725fc', 'Die SWOT-Analyse ist eine Methode zur Bewertung von Stärken (Strengths), Schwächen (Weaknesses), Chancen (Opportunities) und Bedrohungen (Threats) eines Unternehmens.', true),
                                                          ('9c3e5e09-fd59-416b-b1e4-d8d1ff83e057', 'a9a35210-c204-4a74-93dd-529b67c725fc', 'Die SWOT-Analyse ist eine Finanzanalyse für Unternehmen.', false),
                                                          ('d77ff1f7-7e02-4c8f-ae77-0c2f6a9131cc', 'a9a35210-c204-4a74-93dd-529b67c725fc', 'Die SWOT-Analyse ist eine Methode zur Kundenbewertung.', false);


INSERT INTO chatbot_prompt (id, text) VALUES ('550e8400-e29b-41d4-a716-446655440000', 'Hey there! Welcome to our little corner of the internet! I''m Eugen, your friendly chat companion. How''s your day going?'),
                                     ('550e8400-e29b-41d4-a716-446655440001', 'Great stuff! I''m here to lend a hand. What seems to be the issue you''re facing today?'),
                                     ('550e8400-e29b-41d4-a716-446655440002', 'Hmm... I think you should contact our service via telephone or write a ticket. Please choose one option.'),
                                     ('550e8400-e29b-41d4-a716-446655440003', 'Great choice! Just call +49190123456789 and Timo, our friendly phone service person, will take care of you! He is available from 8h till 17h on weekdays. Thank you and good bye :)'),
                                     ('550e8400-e29b-41d4-a716-446655440004', 'I am very sorry! What kind of technical problem did you encounter?'),
                                     ('550e8400-e29b-41d4-a716-446655440005', 'Great choice! Just click on the link below and create a ticket for our staff! Thank you and good bye :)'),
                                     ('550e8400-e29b-41d4-a716-446655440006', 'Let''s tackle this content issue together. Could you share a bit more detail so I can swoop in with the right solution?'),
                                     ('550e8400-e29b-41d4-a716-446655440007', 'Ah okay! First, thank you that you want to improve our website! Click on the "Contribute" button in the main menu at top of the website. There you will find further instructions.');

INSERT INTO chatbot_choice (prompt_id, id, choice_text) VALUES
                                                        ('550e8400-e29b-41d4-a716-446655440000', '550e8400-e29b-41d4-a716-446655440011', 'Amazing!'),
                                                        ('550e8400-e29b-41d4-a716-446655440000', '550e8400-e29b-41d4-a716-446655440012', 'Very great!'),
                                                        ('550e8400-e29b-41d4-a716-446655440000', '550e8400-e29b-41d4-a716-446655440013', 'Great!'),
                                                        ('550e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440014', 'I have a problem with my user account.'),
                                                        ('550e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440015', 'I want to report a content related issue.'),
                                                        ('550e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440016', 'I have a technical issue.'),
                                                        ('550e8400-e29b-41d4-a716-446655440001', '550e8400-e29b-41d4-a716-446655440017', 'I have a completely different problem.'),
                                                        ('550e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440018', 'I want to call the support.'),
                                                        ('550e8400-e29b-41d4-a716-446655440002', '550e8400-e29b-41d4-a716-446655440019', 'I want to write a ticket and get contacted by email.'),
                                                        ('550e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440020', 'The website is not rendering correctly.'),
                                                        ('550e8400-e29b-41d4-a716-446655440004', '550e8400-e29b-41d4-a716-446655440021', 'The website is loading slowly.'),
                                                        ('550e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440022', 'I want to add new content and I don''t know how.'),
                                                        ('550e8400-e29b-41d4-a716-446655440006', '550e8400-e29b-41d4-a716-446655440023', 'I want to report a mistake in the content.');
