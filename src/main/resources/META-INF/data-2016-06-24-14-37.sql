--
-- PostgreSQL database dump
--

-- Dumped from database version 9.5.1
-- Dumped by pg_dump version 9.5.1

-- Started on 2016-06-24 14:37:29

SET statement_timeout = 0;
SET lock_timeout = 0;
SET client_encoding = 'UTF8';
SET standard_conforming_strings = on;
SET check_function_bodies = false;
SET client_min_messages = warning;
SET row_security = off;

SET search_path = public, pg_catalog;

--
-- TOC entry 2426 (class 0 OID 35219)
-- Dependencies: 187
-- Data for Name: aditivo; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2512 (class 0 OID 0)
-- Dependencies: 186
-- Name: aditivo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('aditivo_id_seq', 1, false);


--
-- TOC entry 2428 (class 0 OID 35230)
-- Dependencies: 189
-- Data for Name: alocacao; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2513 (class 0 OID 0)
-- Dependencies: 188
-- Name: alocacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('alocacao_id_seq', 1, false);


--
-- TOC entry 2430 (class 0 OID 35238)
-- Dependencies: 191
-- Data for Name: cargooufuncao; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO cargooufuncao (id, criadoem, destruidoem, vigenteate, vigentedesde, atribuicoes, descricao, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, NULL, 'Motorista (CNH AD)');
INSERT INTO cargooufuncao (id, criadoem, destruidoem, vigenteate, vigentedesde, atribuicoes, descricao, nome) VALUES (2, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, NULL, 'Copeira');
INSERT INTO cargooufuncao (id, criadoem, destruidoem, vigenteate, vigentedesde, atribuicoes, descricao, nome) VALUES (3, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, NULL, 'Serviços gerais');
INSERT INTO cargooufuncao (id, criadoem, destruidoem, vigenteate, vigentedesde, atribuicoes, descricao, nome) VALUES (4, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, NULL, 'Vigilância');


--
-- TOC entry 2514 (class 0 OID 0)
-- Dependencies: 190
-- Name: cargooufuncao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('cargooufuncao_id_seq', 4, true);


--
-- TOC entry 2432 (class 0 OID 35249)
-- Dependencies: 193
-- Data for Name: colaborador; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2515 (class 0 OID 0)
-- Dependencies: 192
-- Name: colaborador_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('colaborador_id_seq', 1, false);


--
-- TOC entry 2434 (class 0 OID 35257)
-- Dependencies: 195
-- Data for Name: contavinculada; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2516 (class 0 OID 0)
-- Dependencies: 194
-- Name: contavinculada_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('contavinculada_id_seq', 1, false);


--
-- TOC entry 2436 (class 0 OID 35268)
-- Dependencies: 197
-- Data for Name: contrato; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata, contasvinculadas, inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (5, '2014-06-14 00:00:00', NULL, '2019-12-31 00:00:00', '2014-01-01 00:00:00', NULL, '2014-07-20', NULL, NULL, '29/2013', 'Postos de trabalho para motoristas', 'Proad 42/2013', NULL, NULL, 4, 4);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata, contasvinculadas, inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (6, '2014-06-14 00:00:00', NULL, '2019-12-31 00:00:00', '2014-01-01 00:00:00', NULL, '2015-06-13', NULL, NULL, '29/2013', 'Postos de trabalho para motoristas', 'Proad 42/2013', NULL, NULL, 4, 4);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata, contasvinculadas, inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (1, '2016-06-22 00:00:00', NULL, '2021-05-31 00:00:00', '2016-06-01 00:00:00', NULL, '2016-05-20', NULL, NULL, '14/2016', 'Postos de trabalho para serviços gerais', 'Proad 12/2016', NULL, NULL, NULL, 1);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata, contasvinculadas, inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (2, '2016-03-12 00:00:00', NULL, '2017-03-31 00:00:00', '2016-04-01 00:00:00', NULL, '2016-03-11', NULL, NULL, '13/2016', 'Postos de trabalho para vigilância', 'Proad 08/2016', NULL, NULL, NULL, 2);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata, contasvinculadas, inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (3, '2015-09-22 00:00:00', NULL, '2020-09-30 00:00:00', '2015-10-01 00:00:00', NULL, '2015-09-13', NULL, NULL, '07/2015', 'Postos de trabalho para copeira', 'Proad 03/2015', NULL, NULL, NULL, 3);
INSERT INTO contrato (id, criadoem, destruidoem, vigenteate, vigentedesde, arp, assinaturadata, contasvinculadas, inteiroteor, numero, objeto, processo, regimetributacao, situacao, aditivode_id, contratado_id) VALUES (4, '2014-06-14 00:00:00', NULL, '2019-12-31 00:00:00', '2014-01-01 00:00:00', NULL, '2013-12-10', NULL, NULL, '29/2013', 'Postos de trabalho para motoristas', 'Proad 42/2013', NULL, NULL, NULL, 4);


--
-- TOC entry 2421 (class 0 OID 30565)
-- Dependencies: 182
-- Data for Name: contrato_aditivo; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2422 (class 0 OID 31799)
-- Dependencies: 183
-- Data for Name: contrato_contavinculada; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2437 (class 0 OID 35277)
-- Dependencies: 198
-- Data for Name: contrato_faturamento; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2517 (class 0 OID 0)
-- Dependencies: 196
-- Name: contrato_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('contrato_id_seq', 1, false);


--
-- TOC entry 2438 (class 0 OID 35280)
-- Dependencies: 199
-- Data for Name: contrato_postodetrabalho; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2440 (class 0 OID 35285)
-- Dependencies: 201
-- Data for Name: email; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2518 (class 0 OID 0)
-- Dependencies: 200
-- Name: email_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('email_id_seq', 1, false);


--
-- TOC entry 2442 (class 0 OID 35296)
-- Dependencies: 203
-- Data for Name: encargo; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'PIS');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (2, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'COFINS');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (3, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'INSS');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (4, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'IRRF');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (5, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'CSLL');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (6, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'ISSQN');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (7, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Retenção férias');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (8, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Retenção 1/3 sobre férias');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (9, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Retenção 13º salário');
INSERT INTO encargo (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (10, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Retenção incidência de encargos previdenciários e fgts sobre outras verbas');


--
-- TOC entry 2423 (class 0 OID 31827)
-- Dependencies: 184
-- Data for Name: encargo_contrato; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2443 (class 0 OID 35302)
-- Dependencies: 204
-- Data for Name: encargo_encargoaliquota; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2519 (class 0 OID 0)
-- Dependencies: 202
-- Name: encargo_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('encargo_id_seq', 10, true);


--
-- TOC entry 2445 (class 0 OID 35307)
-- Dependencies: 206
-- Data for Name: encargoaliquota; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2446 (class 0 OID 35313)
-- Dependencies: 207
-- Data for Name: encargoaliquota_contrato; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2520 (class 0 OID 0)
-- Dependencies: 205
-- Name: encargoaliquota_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('encargoaliquota_id_seq', 1, false);


--
-- TOC entry 2448 (class 0 OID 35318)
-- Dependencies: 209
-- Data for Name: endereco; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2521 (class 0 OID 0)
-- Dependencies: 208
-- Name: endereco_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('endereco_id_seq', 1, false);


--
-- TOC entry 2450 (class 0 OID 35329)
-- Dependencies: 211
-- Data for Name: faturamento; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2424 (class 0 OID 31863)
-- Dependencies: 185
-- Data for Name: faturamento_encargoaliquota; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2522 (class 0 OID 0)
-- Dependencies: 210
-- Name: faturamento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('faturamento_id_seq', 1, false);


--
-- TOC entry 2452 (class 0 OID 35337)
-- Dependencies: 213
-- Data for Name: faturamentoitem; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2523 (class 0 OID 0)
-- Dependencies: 212
-- Name: faturamentoitem_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('faturamentoitem_id_seq', 1, false);


--
-- TOC entry 2454 (class 0 OID 35345)
-- Dependencies: 215
-- Data for Name: faturamentoitemevento; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2524 (class 0 OID 0)
-- Dependencies: 214
-- Name: faturamentoitemevento_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('faturamentoitemevento_id_seq', 1, false);


--
-- TOC entry 2456 (class 0 OID 35353)
-- Dependencies: 217
-- Data for Name: fiscal; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2525 (class 0 OID 0)
-- Dependencies: 216
-- Name: fiscal_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('fiscal_id_seq', 1, false);


--
-- TOC entry 2458 (class 0 OID 35361)
-- Dependencies: 219
-- Data for Name: fiscalespecie; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO fiscalespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Fiscal técnico');
INSERT INTO fiscalespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (2, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Fiscal do contrato');
INSERT INTO fiscalespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (3, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Fiscal administrativo');
INSERT INTO fiscalespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (4, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Fiscal do demandante');


--
-- TOC entry 2526 (class 0 OID 0)
-- Dependencies: 218
-- Name: fiscalespecie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('fiscalespecie_id_seq', 4, true);


--
-- TOC entry 2460 (class 0 OID 35369)
-- Dependencies: 221
-- Data for Name: glosa; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2527 (class 0 OID 0)
-- Dependencies: 220
-- Name: glosa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('glosa_id_seq', 1, false);


--
-- TOC entry 2462 (class 0 OID 35380)
-- Dependencies: 223
-- Data for Name: glosaespecie; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO glosaespecie (id, criadoem, destruidoem, vigenteate, vigentedesde, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', 'Posto de trabalho vago');


--
-- TOC entry 2528 (class 0 OID 0)
-- Dependencies: 222
-- Name: glosaespecie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('glosaespecie_id_seq', 1, true);


--
-- TOC entry 2464 (class 0 OID 35388)
-- Dependencies: 225
-- Data for Name: jornada; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO jornada (id, criadoem, destruidoem, vigenteate, vigentedesde, descricao, nome) VALUES (1, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, '12x36');
INSERT INTO jornada (id, criadoem, destruidoem, vigenteate, vigentedesde, descricao, nome) VALUES (2, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, '44h semanais');
INSERT INTO jornada (id, criadoem, destruidoem, vigenteate, vigentedesde, descricao, nome) VALUES (3, '2016-06-24 00:00:00', NULL, NULL, '2000-01-01 00:00:00', NULL, '36h semanais');


--
-- TOC entry 2529 (class 0 OID 0)
-- Dependencies: 224
-- Name: jornada_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('jornada_id_seq', 3, true);


--
-- TOC entry 2466 (class 0 OID 35399)
-- Dependencies: 227
-- Data for Name: liberacao; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2530 (class 0 OID 0)
-- Dependencies: 226
-- Name: liberacao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('liberacao_id_seq', 1, false);


--
-- TOC entry 2468 (class 0 OID 35410)
-- Dependencies: 229
-- Data for Name: pessoa; Type: TABLE DATA; Schema: public; Owner: contratosADM
--

INSERT INTO pessoa (dtype, id, criadoem, destruidoem, vigenteate, vigentedesde, cnpj, inscricaoestadual, nomefantasia, razaosocial, cpf, ctps, nascimentodata, nome, pispasep, rgnumero, rgogaoexpedidor, rguf) VALUES ('PessoaJuridica', 4, NULL, NULL, NULL, NULL, '09576957000155', 'NÃO INFORMADA', 'Presta Serviços Técnicos', 'Presta Serviços Técnicos LTDA - EPP ', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pessoa (dtype, id, criadoem, destruidoem, vigenteate, vigentedesde, cnpj, inscricaoestadual, nomefantasia, razaosocial, cpf, ctps, nascimentodata, nome, pispasep, rgnumero, rgogaoexpedidor, rguf) VALUES ('PessoaJuridica', 2, NULL, NULL, NULL, NULL, '09576957000155', 'NÃO INFORMADA', 'Dicentro', 'Raquel Rockengach ME', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pessoa (dtype, id, criadoem, destruidoem, vigenteate, vigentedesde, cnpj, inscricaoestadual, nomefantasia, razaosocial, cpf, ctps, nascimentodata, nome, pispasep, rgnumero, rgogaoexpedidor, rguf) VALUES ('PessoaJuridica', 3, NULL, NULL, NULL, NULL, '08830513000132', 'NÃO INFORMADA', 'Universal Segurança', 'Universal Segurança LTDA - EPP', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);
INSERT INTO pessoa (dtype, id, criadoem, destruidoem, vigenteate, vigentedesde, cnpj, inscricaoestadual, nomefantasia, razaosocial, cpf, ctps, nascimentodata, nome, pispasep, rgnumero, rgogaoexpedidor, rguf) VALUES ('PessoaJuridica', 1, NULL, NULL, NULL, NULL, '00081160000102', 'NÃO INFORMADA', 'Luppa', 'Luppa Administradora de Serviços e Representações Comerciais Ltda', NULL, NULL, NULL, NULL, NULL, NULL, NULL, NULL);


--
-- TOC entry 2469 (class 0 OID 35419)
-- Dependencies: 230
-- Data for Name: pessoa_email; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2470 (class 0 OID 35422)
-- Dependencies: 231
-- Data for Name: pessoa_endereco; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2531 (class 0 OID 0)
-- Dependencies: 228
-- Name: pessoa_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('pessoa_id_seq', 4, true);


--
-- TOC entry 2471 (class 0 OID 35425)
-- Dependencies: 232
-- Data for Name: pessoa_telefone; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2473 (class 0 OID 35430)
-- Dependencies: 234
-- Data for Name: postodetrabalho; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2532 (class 0 OID 0)
-- Dependencies: 233
-- Name: postodetrabalho_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('postodetrabalho_id_seq', 1, false);


--
-- TOC entry 2475 (class 0 OID 35438)
-- Dependencies: 236
-- Data for Name: retencao; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2533 (class 0 OID 0)
-- Dependencies: 235
-- Name: retencao_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('retencao_id_seq', 1, false);


--
-- TOC entry 2477 (class 0 OID 35446)
-- Dependencies: 238
-- Data for Name: retencaoespecie; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2534 (class 0 OID 0)
-- Dependencies: 237
-- Name: retencaoespecie_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('retencaoespecie_id_seq', 1, false);


--
-- TOC entry 2420 (class 0 OID 29459)
-- Dependencies: 181
-- Data for Name: servidor; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2479 (class 0 OID 35454)
-- Dependencies: 240
-- Data for Name: telefone; Type: TABLE DATA; Schema: public; Owner: contratosADM
--



--
-- TOC entry 2535 (class 0 OID 0)
-- Dependencies: 239
-- Name: telefone_id_seq; Type: SEQUENCE SET; Schema: public; Owner: contratosADM
--

SELECT pg_catalog.setval('telefone_id_seq', 1, false);


-- Completed on 2016-06-24 14:37:31

--
-- PostgreSQL database dump complete
--

