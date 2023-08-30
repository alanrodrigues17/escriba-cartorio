CREATE TABLE IF NOT EXISTS situacao_cartorio (
	 id VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL
);

INSERT INTO situacao_cartorio (id, nome) VALUES ('SIT_ATIVO', 'Ativo');
INSERT INTO situacao_cartorio (id, nome) VALUES ('SIT_BLOQUEADO', 'Bloqueado');

CREATE TABLE IF NOT EXISTS atribuicao_cartorio (
	 id VARCHAR(20) PRIMARY KEY,
    nome VARCHAR(50) NOT NULL,
    situacao BOOLEAN DEFAULT TRUE NOT NULL
);

CREATE TABLE IF NOT EXISTS cartorios (
	 id INT PRIMARY KEY,
    nome VARCHAR(150) NOT NULL,
    observacao VARCHAR(250),
    situacao_id VARCHAR(20) NOT NULL,
    FOREIGN KEY (situacao_id) REFERENCES situacao_cartorio(id)
);

CREATE TABLE IF NOT EXISTS cartorio_atribuicoes (
    cartorio_id INT,
    atribuicao_id VARCHAR(20),
    PRIMARY KEY (cartorio_id, atribuicao_id),
    FOREIGN KEY (cartorio_id) REFERENCES cartorios(id),
    FOREIGN KEY (atribuicao_id) REFERENCES atribuicao_cartorio(id)
);

ALTER TABLE situacao_cartorio RENAME TO situacao;
ALTER TABLE atribuicao_cartorio RENAME TO atribuicao;

