CREATE TABLE funcionarios (
    id BIGINT AUTO_INCREMENT PRIMARY KEY,
    nome VARCHAR(255),
    data_admissao DATE,
    data_ultimas_ferias DATE,
    dias_transcorridos INT,
    status VARCHAR(50)
);
