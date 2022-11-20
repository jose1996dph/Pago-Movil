package com.example.pagomovil.models

data class Payment (
    var bank: String,
    var dni: String,
    var mount: String,
    var operator: String,
    var phone: String
)