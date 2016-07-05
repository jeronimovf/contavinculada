--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-06-28 08:09:09

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 2427 (class 0 OID 35219)
-- Dependencies: 187
-- Data for Name: aditivo; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2513 (class 0 OID 0)
-- Dependencies: 186
-- Name: aditivo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('aditivo_id_seq', 1, false);


--
-- TOC entry 2431 (class 0 OID 36083)
-- Dependencies: 191
-- Data for Name: alocacao; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2514 (class 0 OID 0)
-- Dependencies: 190
-- Name: alocacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('alocacao_id_seq', 1, false);


--
-- TOC entry 2433 (class 0 OID 36091)
-- Dependencies: 193
-- Data for Name: cargooufuncao; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO cargooufuncao (id, criadoem, destruidoem, vigenteate, vigentedesde, atribuicoes, descricao, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, NULL, 'Motorista (CNH AD)');
INSERT INTO cargooufuncao (id, criadoem, destruidoem, vigenteate, vigentedesde, atribuicoes, descricao, nome) VALUES (2, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, NULL, 'Copeira');
INSERT INTO cargooufuncao (id, criadoem, destruidoem, vigenteate, vigentedesde, atribuicoes, descricao, nome) VALUES (3, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, NULL, 'Serviços gerais');
INSERT INTO cargooufuncao (id, criadoem, destruidoem, vigenteate, vigentedesde, atribuicoes, descricao, nome) VALUES (4, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, NULL, 'Vigilância');


--
-- TOC entry 2515 (class 0 OID 0)
-- Dependencies: 192
-- Name: cargooufuncao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('cargooufuncao_id_seq', 4, true);


--
-- TOC entry 2435 (class 0 OID 36102)
-- Dependencies: 195
-- Data for Name: colaborador; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2516 (class 0 OID 0)
-- Dependencies: 194
-- Name: colaborador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('colaborador_id_seq', 1, false);


--
-- TOC entry 2437 (class 0 OID 36110)
-- Dependencies: 197
-- Data for Name: contavinculada; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2517 (class 0 OID 0)
-- Dependencies: 196
-- Name: contavinculada_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('contavinculada_id_seq', 1, false);



--
-- TOC entry 2422 (class 0 OID 30565)
-- Dependencies: 182
-- Data for Name: contrato_aditivo; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2423 (class 0 OID 31799)
-- Dependencies: 183
-- Data for Name: contrato_contavinculada; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2440 (class 0 OID 36130)
-- Dependencies: 200
-- Data for Name: contrato_faturamento; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2518 (class 0 OID 0)
-- Dependencies: 198
-- Name: contrato_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('contrato_id_seq', 1, false);


--
-- TOC entry 2441 (class 0 OID 36133)
-- Dependencies: 201
-- Data for Name: contrato_postodetrabalho; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2443 (class 0 OID 36138)
-- Dependencies: 203
-- Data for Name: email; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2519 (class 0 OID 0)
-- Dependencies: 202
-- Name: email_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('email_id_seq', 1, false);


--
-- TOC entry 2445 (class 0 OID 36149)
-- Dependencies: 205
-- Data for Name: encargo; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'PIS');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (2, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'COFINS');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (3, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'INSS');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (4, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'IRRF');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (5, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'CSLL');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (6, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'ISSQN');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (7, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'Retenção férias');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (8, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'Retenção 1/3 sobre férias');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (9, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'Retenção 13º salário');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, isretencaocontavinculada, nome) VALUES (10, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, 'Retenção incidência de encargos previdenciários e fgts sobre outras verbas');

select * from encargo;
--
-- TOC entry 2424 (class 0 OID 31827)
-- Dependencies: 184
-- Data for Name: encargo_contrato; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2446 (class 0 OID 36155)
-- Dependencies: 206
-- Data for Name: encargo_encargoaliquota; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2520 (class 0 OID 0)
-- Dependencies: 204
-- Name: encargo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('encargo_id_seq', 10, true);


--
-- TOC entry 2448 (class 0 OID 36160)
-- Dependencies: 208
-- Data for Name: encargoaliquota; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2449 (class 0 OID 36166)
-- Dependencies: 209
-- Data for Name: encargoaliquota_contrato; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2521 (class 0 OID 0)
-- Dependencies: 207
-- Name: encargoaliquota_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('encargoaliquota_id_seq', 1, false);


--
-- TOC entry 2451 (class 0 OID 36171)
-- Dependencies: 211
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2522 (class 0 OID 0)
-- Dependencies: 210
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('endereco_id_seq', 1, false);


--
-- TOC entry 2453 (class 0 OID 36182)
-- Dependencies: 213
-- Data for Name: faturamento; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2425 (class 0 OID 31863)
-- Dependencies: 185
-- Data for Name: faturamento_encargoaliquota; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2523 (class 0 OID 0)
-- Dependencies: 212
-- Name: faturamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('faturamento_id_seq', 1, false);


--
-- TOC entry 2455 (class 0 OID 36190)
-- Dependencies: 215
-- Data for Name: faturamentoitem; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2524 (class 0 OID 0)
-- Dependencies: 214
-- Name: faturamentoitem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('faturamentoitem_id_seq', 1, false);


--
-- TOC entry 2457 (class 0 OID 36198)
-- Dependencies: 217
-- Data for Name: faturamentoitemevento; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2525 (class 0 OID 0)
-- Dependencies: 216
-- Name: faturamentoitemevento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('faturamentoitemevento_id_seq', 1, false);


--
-- TOC entry 2459 (class 0 OID 36206)
-- Dependencies: 219
-- Data for Name: fiscal; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2526 (class 0 OID 0)
-- Dependencies: 218
-- Name: fiscal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('fiscal_id_seq', 1, false);


--
-- TOC entry 2461 (class 0 OID 36214)
-- Dependencies: 221
-- Data for Name: fiscalespecie; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO fiscalespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Fiscal técnico');
INSERT INTO fiscalespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (2, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Fiscal do contrato');
INSERT INTO fiscalespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (3, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Fiscal administrativo');
INSERT INTO fiscalespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (4, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Fiscal do demandante');

select * from fiscalespecie;
--
-- TOC entry 2527 (class 0 OID 0)
-- Dependencies: 220
-- Name: fiscalespecie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('fiscalespecie_id_seq', 4, true);


--
-- TOC entry 2463 (class 0 OID 36222)
-- Dependencies: 223
-- Data for Name: glosa; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2528 (class 0 OID 0)
-- Dependencies: 222
-- Name: glosa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('glosa_id_seq', 1, false);


--
-- TOC entry 2465 (class 0 OID 36233)
-- Dependencies: 225
-- Data for Name: glosaespecie; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO glosaespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Posto de trabalho vago');


--
-- TOC entry 2529 (class 0 OID 0)
-- Dependencies: 224
-- Name: glosaespecie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('glosaespecie_id_seq', 1, true);


--
-- TOC entry 2467 (class 0 OID 36241)
-- Dependencies: 227
-- Data for Name: jornada; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO jornada (id, criadoem, destruidoem, vigenteate, vigentedesde, descricao, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, '12x36');
INSERT INTO jornada (id, criadoem, destruidoem, vigenteate, vigentedesde, descricao, nome) VALUES (2, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, '44h semanais');
INSERT INTO jornada (id, criadoem, destruidoem, vigenteate, vigentedesde, descricao, nome) VALUES (3, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, '36h semanais');


--
-- TOC entry 2530 (class 0 OID 0)
-- Dependencies: 226
-- Name: jornada_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('jornada_id_seq', 3, true);


--
-- TOC entry 2469 (class 0 OID 36252)
-- Dependencies: 229
-- Data for Name: liberacao; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2531 (class 0 OID 0)
-- Dependencies: 228
-- Name: liberacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('liberacao_id_seq', 1, false);


--
-- TOC entry 2471 (class 0 OID 36263)
-- Dependencies: 231
-- Data for Name: pessoa; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO pessoa (dtype, id, criadoem, destruidoem, vigenteate, vigentedesde, cnpj, inscricaoestadual, nomefantasia, razaosocial, cpf, ctps, nascimentodata, nome, pispasep, rgnumero, rgogaoexpedidor, rguf) VALUES ('PessoaJuridica', 4, '2016-06-28', NULL, NULL, NULL, '09576957000155', 'NÃO INFORMADA', 'Presta Serviços Técnicos', 'Presta Serviços Técnicos LTDA - EPP ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pessoa (dtype, id, criadoem, destruidoem, vigenteate, vigentedesde, cnpj, inscricaoestadual, nomefantasia, razaosocial, cpf, ctps, nascimentodata, nome, pispasep, rgnumero, rgogaoexpedidor, rguf) VALUES ('PessoaJuridica', 2, '2016-06-28', NULL, NULL, NULL, '09576957000155', 'NÃO INFORMADA', 'Dicentro', 'Raquel Rockengach ME', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pessoa (dtype, id, criadoem, destruidoem, vigenteate, vigentedesde, cnpj, inscricaoestadual, nomefantasia, razaosocial, cpf, ctps, nascimentodata, nome, pispasep, rgnumero, rgogaoexpedidor, rguf) VALUES ('PessoaJuridica', 3, '2016-06-28', NULL, NULL, NULL, '08830513000132', 'NÃO INFORMADA', 'Universal Segurança', 'Universal Segurança LTDA - EPP', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pessoa (dtype, id, criadoem, destruidoem, vigenteate, vigentedesde, cnpj, inscricaoestadual, nomefantasia, razaosocial, cpf, ctps, nascimentodata, nome, pispasep, rgnumero, rgogaoexpedidor, rguf) VALUES ('PessoaJuridica', 1, '2016-06-28', NULL, NULL, NULL, '00081160000102', 'NÃO INFORMADA', 'Luppa', 'Luppa Administradora de Serviços e Representações Comerciais Ltda', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);

select * from pessoa;
--
-- TOC entry 2472 (class 0 OID 36272)
-- Dependencies: 232
-- Data for Name: pessoa_email; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2473 (class 0 OID 36275)
-- Dependencies: 233
-- Data for Name: pessoa_endereco; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2532 (class 0 OID 0)
-- Dependencies: 230
-- Name: pessoa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('pessoa_id_seq', 4, true);


--
-- TOC entry 2474 (class 0 OID 36278)
-- Dependencies: 234
-- Data for Name: pessoa_telefone; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2476 (class 0 OID 36283)
-- Dependencies: 236
-- Data for Name: postodetrabalho; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2533 (class 0 OID 0)
-- Dependencies: 235
-- Name: postodetrabalho_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('postodetrabalho_id_seq', 1, false);


--
-- TOC entry 2478 (class 0 OID 36291)
-- Dependencies: 238
-- Data for Name: retencao; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2534 (class 0 OID 0)
-- Dependencies: 237
-- Name: retencao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('retencao_id_seq', 1, false);


--
-- TOC entry 2429 (class 0 OID 35446)
-- Dependencies: 189
-- Data for Name: retencaoespecie; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2535 (class 0 OID 0)
-- Dependencies: 188
-- Name: retencaoespecie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('retencaoespecie_id_seq', 1, false);


--
-- TOC entry 2421 (class 0 OID 29459)
-- Dependencies: 181
-- Data for Name: servidor; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2480 (class 0 OID 36299)
-- Dependencies: 240
-- Data for Name: telefone; Type: TABLE DATA; Schema: public; Owner: contratosADM
--


--
-- TOC entry 2439 (class 0 OID 36121)
-- Dependencies: 199
-- Data for Name: contrato; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata,  inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (1, '2016-06-22 00:00:00', NULL, '2021-05-31 00:00:00', '2016-06-01 00:00:00', NULL, '2016-05-20', NULL, '14/2016', 'Postos de trabalho para serviços gerais', 'Proad 12/2016', NULL, NULL, NULL, 1);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata,  inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (2, '2016-03-12 00:00:00', NULL, '2017-03-31 00:00:00', '2016-04-01 00:00:00', NULL, '2016-03-11', NULL, '13/2016', 'Postos de trabalho para vigilância', 'Proad 08/2016', NULL, NULL, NULL, 2);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata,  inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (3, '2015-09-22 00:00:00', NULL, '2020-09-30 00:00:00', '2015-10-01 00:00:00', NULL, '2015-09-13', NULL, '07/2015', 'Postos de trabalho para copeira', 'Proad 03/2015', NULL, NULL, NULL, 3);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata,  inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (4, '2014-06-14 00:00:00', NULL, '2019-12-31 00:00:00', '2014-01-01 00:00:00', NULL, '2013-12-10', NULL, '29.1/2013', 'Postos de trabalho para motoristas', 'Proad 42/2013', NULL, NULL, NULL, 4);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata,  inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (5, '2014-06-14 00:00:00', NULL, '2019-12-31 00:00:00', '2014-01-01 00:00:00', NULL, '2014-07-20', NULL, '29.2/2013', 'Postos de trabalho para motoristas', 'Proad 42/2013', NULL, NULL, 4, 4);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata,  inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (6, '2014-06-14 00:00:00', NULL, '2019-12-31 00:00:00', '2014-01-01 00:00:00', NULL, '2015-06-13', NULL, '29.3/2013', 'Postos de trabalho para motoristas', 'Proad 42/2013', NULL, NULL, 4, 4);


--
-- TOC entry 2536 (class 0 OID 0)
-- Dependencies: 239
-- Name: telefone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('telefone_id_seq', 1, false);


-- Completed on 2016-06-28 08:09:10

--
-- PostgreSQL database dump complete
--

SELECT * FROM PESSOA