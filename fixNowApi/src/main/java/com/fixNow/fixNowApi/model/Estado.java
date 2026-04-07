package com.fixNow.fixNowApi.model;
// Utilizamos enum porque permite definir un conjunto cerrado de valores validos para un campo. En lugar de usar cualquier String, el compilador solo acepta los valores que tu defines.
// Spring Boot maneja las excepciones de forma automatica.
public enum Estado {
    ABIERTA,
    EN_PROCESO,
    RESUELTA,
    CERRADA
}
