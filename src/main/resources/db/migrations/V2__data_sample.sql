-- Sistema de Gerenciamento Hospitalar da Mentorama
-- Inserção de dados de amostra

INSERT INTO pacientes
        (nome, telefone, data_nascimento)
    VALUES
        ('João da Silva', '11999999999', '1980-01-20'),
        ('Maria Joana Alves', '12888888888', '1974-11-03'),
        ('Paulo Roberto Souza', '11999999999', '2015-04-14');

INSERT INTO medicos
        (nome, cargo, departamento, telefone)
    VALUES
        ('Heitor Nunes', 'Médico Intensivista', 'UTI Adulto', '11999999999'),
        ('Cláudio José', 'Pediatra', 'UTI Pediátrica', '11999999999'),
        ('José Borges', 'Cardiologista', 'UTI Adulto', '11999999999'),
        ('João Souza', 'Clínico Geral', 'Pronto-Atendimento', '11999999999'),
        ('Vitor Salgado', 'Clínico Geral', 'UTI Adulto', '11999999999');

INSERT INTO historico_internacoes
        (id_paciente, entrada_paciente, saida_paciente, diagnostico, matricula_medico)
    VALUES
        ('1', '2023-05-03 10:30:00', '2023-05-04 11:45:00', 'Internação para pequena circurgia na mão', 4),
        ('1', '2023-05-10 09:00:00', '2023-05-11 08:34:00', 'Retorno para finalização do tratamento', 4),
        ('2', '2023-01-11 20:40:00', '2023-01-15 12:35:00', 'Tratamento do coração', 3),
        ('2', '2023-01-19 06:10:20', '2023-01-19 22:08:50', 'Exames', 3),
        ('2', '2023-03-02 11:05:30', '2023-03-03 21:43:34', 'Cirurgia cardíaca', 3),
        ('3', '2023-04-15 17:57:10', '2023-04-16 07:53:32', 'Tratamento de fratura', 2);