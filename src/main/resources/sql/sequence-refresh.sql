/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
/**
 * Author:  j129-9
 * Created: 18/10/2016
 */

-- Oracle cannot change the start value of a sequence.
-- Must drop and re-create.
-- Exclusão de todas as sequences do schema
SET SERVEROUTPUT ON;

--Gera para output os comandos create sequence
DECLARE
  tablename VARCHAR2(50 CHAR);
  sequencename VARCHAR2(50 CHAR);
  createSeqInstruction VARCHAR2(512);
  deleteSeqInstruction VARCHAR2(512);  
  selectLastInstruction VARCHAR2(512);
  colExists NUMBER := 0;
  lastId NUMBER := 0;
  CURSOR myTables IS 
    SELECT ut.table_name AS tname FROM USER_TABLES ut;
BEGIN
  
  --Bye Tables!
  FOR i IN myTables LOOP
    tablename := i.tname;
    sequencename := tablename || '_SEQ';
    SELECT COUNT(*) INTO colExists FROM USER_TAB_COLS WHERE table_name = tablename AND column_name = 'ID';
    IF (colExists >0) THEN
      EXECUTE IMMEDIATE 'SELECT MAX(ID) FROM ' || i.tname INTO lastId;  
      IF (lastId IS NULL) THEN
        lastId:= 1;
      ELSE
        lastId := lastId + 1;
      END IF;
      deleteSeqInstruction := 'DROP SEQUENCE '|| sequencename || ';';
      createSeqInstruction := 'CREATE SEQUENCE '|| sequencename || ' START WITH ' || TO_CHAR(lastId) || ' INCREMENT BY 1;';                          
      DBMS_OUTPUT.PUT_LINE (deleteSeqInstruction);      
      DBMS_OUTPUT.PUT_LINE (createSeqInstruction);      
    END IF;
  END LOOP;
END;