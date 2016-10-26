/* 
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */

function showBySwitch(switchId,enableIfValue1,enableIfValue2) {
    if(document.getElementById(switchId).checked === true){
        document.getElementById(enableIfValue1).style.visibility='visible';
        document.getElementById(enableIfValue2).style.visibility='hidden';
    }
    else{
        document.getElementById(enableIfValue2).style.visibility='visible';
        document.getElementById(enableIfValue1).style.visibility='hidden';
    }
    
}
