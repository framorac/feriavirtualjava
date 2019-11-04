/*
 * FILE: script.js
 * AUTHOR: Francisco Mora
 */
$(function () {
   console.log('Ready....');
   
   $('.ui.form').form({
     fields: {
       email: {
         identifier  : 'username',
         rules: [
           {
             type   : 'empty',
             prompt : 'Usuario es obligatorio..'
           }
         ]
       },
       password: {
         identifier  : 'password',
         rules: [
           {
             type   : 'empty',
             prompt : 'Password obligatoria..'
           }
         ]
       }
     }
   });
});