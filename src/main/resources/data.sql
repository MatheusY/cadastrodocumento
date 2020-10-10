INSERT INTO perfil (descricao) VALUES ('Admin');
INSERT INTO perfil (descricao) VALUES ('Editor');
INSERT INTO perfil (descricao) VALUES ('Visualizador');

INSERT INTO tipo_documento (nome) VALUES ('CPF');
INSERT INTO tipo_documento (nome) VALUES ('RG');

INSERT INTO uf (sigla, nome) VALUES ('AC','Acre');
INSERT INTO uf (sigla, nome) VALUES ('AL','Alagoas');
INSERT INTO uf (sigla, nome) VALUES ('AP','Amapá');
INSERT INTO uf (sigla, nome) VALUES ('AM','Amazonas');
INSERT INTO uf (sigla, nome) VALUES ('CE','Ceará');
INSERT INTO uf (sigla, nome) VALUES ('DF','Distrito Federal');
INSERT INTO uf (sigla, nome) VALUES ('ES','Espírito Santo');
INSERT INTO uf (sigla, nome) VALUES ('GO','Goiás');
INSERT INTO uf (sigla, nome) VALUES ('MA','Maranhão');
INSERT INTO uf (sigla, nome) VALUES ('MT','Mato Grosso');
INSERT INTO uf (sigla, nome) VALUES ('MS','Mato Grosso do Sul');
INSERT INTO uf (sigla, nome) VALUES ('MG','Minas Gerais');
INSERT INTO uf (sigla, nome) VALUES ('PA','Pará');
INSERT INTO uf (sigla, nome) VALUES ('PB','Paraíba');
INSERT INTO uf (sigla, nome) VALUES ('PR','Paraná');
INSERT INTO uf (sigla, nome) VALUES ('PE','Pernambuco');
INSERT INTO uf (sigla, nome) VALUES ('PI','Piauí');
INSERT INTO uf (sigla, nome) VALUES ('RJ','Rio de Janeiro');
INSERT INTO uf (sigla, nome) VALUES ('RN','Rio Grande do Norte');
INSERT INTO uf (sigla, nome) VALUES ('RS','Rio Grande do Sul');
INSERT INTO uf (sigla, nome) VALUES ('RO','Rondônia');
INSERT INTO uf (sigla, nome) VALUES ('RR','Roraima');
INSERT INTO uf (sigla, nome) VALUES ('SC','Santa Catarina');
INSERT INTO uf (sigla, nome) VALUES ('SP','São Paulo');
INSERT INTO uf (sigla, nome) VALUES ('SE','Sergipe');
INSERT INTO uf (sigla, nome) VALUES ('TO','Tocantins');

INSERT INTO usuario
(data_cadastro, email, senha_hash, usuario, id_perfil, ativo, email_validado, key_email)
VALUES(now(), admin@gmail.com, 'A//eNU/Kx+I=', 'admin', 1, true, true, 9999);

