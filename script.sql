INSERT INTO tb_species (name) VALUES
('Cachorro'),
('Gato'),
('Pássaro'),
('Coelho'),
('Hamster'),
('Tartaruga'),
('Peixe'),
('Cavalo'),
('Porquinho-da-Índia'),
('Lagarto');
 
INSERT INTO tb_breeds (name, id_specie) VALUES
('Labrador', 1),
('Persa', 2),
('Canário', 3),
('Angorá', 2),
('Shih Tzu', 1),
('Coelho Anão', 4),
('Sírio', 5),
('Tigre d\'Água', 6),
('Betta', 7),
('Puro Sangue', 8),
('Americano', 9),
('Leopard Gecko', 10);
 
INSERT INTO tb_customers (name, cpf, phone_number, email, address, active) VALUES
('João Silva', '12345678901', '999999999', 'joao.silva@example.com', 'Rua A, 123', true),
('Maria Oliveira', '10987654321', '888888888', 'maria.oliveira@example.com', 'Rua B, 456', true),
('Carlos Souza', '11122233344', '777777777', 'carlos.souza@example.com', 'Rua C, 789', true),
('Ana Lima', '55566677788', '666666666', 'ana.lima@example.com', 'Rua D, 101', true),
('Pedro Santos', '99988877766', '555555555', 'pedro.santos@example.com', 'Rua E, 202', true),
('Julia Mendes', '44455566677', '444444444', 'julia.mendes@example.com', 'Rua F, 303', true),
('Paula Costa', '33344455566', '333333333', 'paula.costa@example.com', 'Rua G, 404', true),
('Ricardo Ferreira', '22233344455', '222222222', 'ricardo.ferreira@example.com', 'Rua H, 505', true),
('Bianca Rocha', '88877766655', '111111111', 'bianca.rocha@example.com', 'Rua I, 606', true),
('Roberto Lima', '77766655544', '000000000', 'roberto.lima@example.com', 'Rua J, 707', true);
 

INSERT INTO tb_animals (name, date_of_birth, gender, weight, id_customers, id_breeds) VALUES
('Rex', '2021-06-15', 'macho', 25.0, 1, 1),
('Mimi', '2022-06-15', 'fêmea', 4.5, 2, 2),
('Piu', '2023-06-15', 'macho', 0.05, 3, 3),
('Bolinha', '2020-06-15', 'macho', 5.0, 4, 4),
('Max', '2019-06-15', 'macho', 10.0, 5, 5),
('Peludo', '2022-06-15', 'macho', 1.5, 6, 6),
('Fifi', '2023-06-15', 'fêmea', 0.1, 7, 7),
('Tina', '2014-06-15', 'fêmea', 1.0, 8, 8),
('Goldie', '2023-06-15', 'fêmea', 0.05, 9, 9),
('Flash', '2022-06-15', 'macho', 1.2, 10, 10);

 
INSERT INTO tb_workers (name, cpf, email, phone_number, address, functionn, username, passwordd, user_Level, active) VALUES
('Ana Pereira', '22233344455', 'email1@email.com', '666666666', 'Rua D, 101', 'Veterinária', 'anap', 'password123', 1, true),
('Paulo Lima', '33344455566', 'email2@email.com', '555555555', 'Rua E, 202', 'Auxiliar', 'paulol', 'password456', 2, true),
('Carlos Almeida', '44455566677', 'email3@email.com', '444444444', 'Rua F, 303', 'Veterinário', 'carlosa', 'password789', 1, true),
('Maria Figueiredo', '55566677788', 'email4@email.com', '333333333', 'Rua G, 404', 'Recepcionista', 'mariaf', 'password012', 3, true),
('José Silva', '66677788899', 'email5@email.com', '222222222', 'Rua H, 505', 'Auxiliar', 'joses', 'password345', 2, true),
('Mariana Costa', '77788899900', 'email6@email.com', '111111111', 'Rua I, 606', 'Veterinária', 'marianac', 'password678', 1, true),
('Fernanda Rocha', '88899900011', 'email7@email.com', '000000000', 'Rua J, 707', 'Auxiliar', 'fernandar', 'password901', 2, true),
('Ricardo Teixeira', '99900011122', 'email8@email.com', '999999999', 'Rua K, 808', 'Veterinário', 'ricardot', 'password234', 1, true),
('Cláudia Martins', '00011122233', 'email9@email.com', '888888888', 'Rua L, 909', 'Recepcionista', 'claudiam', 'password567', 3, true),
('Fabiana Souza', '11122233344', 'email10@email.com', '777777777', 'Rua M, 101', 'Auxiliar', 'fabianas', 'password890', 2, true);
 
INSERT INTO tb_services (name, price) VALUES
('Consulta', 100.0),
('Vacinação', 50.0),
('Banho e Tosa', 30.0),
('Exame de Sangue', 70.0),
('Raio-X', 150.0),
('Cirurgia', 500.0),
('Ultrassom', 120.0),
('Internação', 200.0),
('Tratamento Dentário', 80.0),
('Microchipagem', 60.0);
 
INSERT INTO tb_appointments (start_time, finish_time,date, id_customers, id_animals, id_workers, id_services, observation) VALUES
('10:00', '10:30','2024-06-21', 1, 1, 1, 1, 'Consulta de rotina'),
('11:00', '11:30','2024-06-21', 1, 2, 2, 1, 'Vacinação anual'),
('14:00', '14:30','2024-06-22', 1, 3, 3, 1, 'Exame de rotina'),
('15:00', '15:30','2024-06-22', 2, 4, 4, 1, 'Vacinação de filhote'),
('16:00', '16:30','2024-06-23', 2, 5, 5, 1, 'Aplicação de medicamentos'),
('17:00', '17:30','2024-06-23', 2, 6, 6, 1, 'Consulta inicial'),
('09:00', '09:30','2024-06-24', 3, 7, 7, 1, 'Exame pré-operatório'),
('10:30', '11:00','2024-06-24', 3, 8, 8, 1, 'Vacinação anual'),
('11:30', '12:00','2024-06-25', 3, 9, 9, 1, 'Consulta de retorno'),
('14:30', '15:00','2024-06-25', 3, 10, 10, 1, 'Aplicação de vacinas');

INSERT INTO tb_vaccines (id, name, price) VALUES
(1, 'Vacina Polivalente', 150.00),
(2, 'Vacina Antirrábica', 80.00),
(3, 'Vacina Gripe Canina', 120.00),
(4, 'Vacina Leptospirose', 90.00),
(5, 'Vacina Giárdia', 70.00),
(6, 'Vacina Leishmaniose', 200.00),
(7, 'Vacina Hepatite Infecciosa Canina', 110.00),
(8, 'Vacina Parainfluenza', 85.00),
(9, 'Vacina Coronavirose Canina', 75.00),
(10, 'Vacina Tosse dos Canis', 95.00);

INSERT INTO tb_medicines (id, name, price) VALUES
(1, 'Antibiótico Amoxicilina', 30.00),
(2, 'Antiparasitário Ivermectina', 25.00),
(3, 'Anti-inflamatório Meloxicam', 40.00),
(4, 'Analgésico Tramadol', 50.00),
(5, 'Antifúngico Cetoconazol', 35.00),
(6, 'Suplemento Vitamínico', 20.00),
(7, 'Antiemético Metoclopramida', 15.00),
(8, 'Antialérgico Loratadina', 10.00),
(9, 'Antidiarreico Metronidazol', 45.00),
(10, 'Protetor Gástrico Omeprazol', 28.00);

 
