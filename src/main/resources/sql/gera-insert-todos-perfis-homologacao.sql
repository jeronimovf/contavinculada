/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  j129-9
 * Created: 06/12/2016
 */
select 'insert into permissao (id, login, fk_perfil, fk_unidade, data_cadastro, usuario_cadastro) values (seq_permissao.nextval,''' || 
perm.login || ''',' || perf.id || ', 355, currenttimestamp, ''J129-9'')',
perf.id, perf.DESCRICAO, perm.LOGIN 
from 
perfil perf, permissao perm 
where 
perf.fk_sistema=8 and perm.FK_PERFIL in (48,56,51,52,53) and perm.LOGIN <> 'J129-9'
ORDER BY
perm.login, perf.descricao
