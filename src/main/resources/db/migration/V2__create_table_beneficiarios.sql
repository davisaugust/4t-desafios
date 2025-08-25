CREATE TABLE beneficiarios (
    id UUID PRIMARY KEY,
    nome_completo VARCHAR(255) NOT NULL,
    cpf VARCHAR(11) NOT NULL UNIQUE,
    data_nascimento DATE NOT NULL,
    status VARCHAR(20) DEFAULT 'ATIVO',
    data_cadastro TIMESTAMP DEFAULT NOW(),
    plano_id UUID NOT NULL,
    FOREIGN KEY (plano_id) REFERENCES plano(id)
);
