package com.jair.ventaspamsac.Aprendizaje

//ejemplo de inyeccion de dependencias con interfaces en kotlin
//un ejemplo sencillo pero que te ayuda mucho a entender estos conceptos

interface IEngine {
     fun start(){}
}
class GasEngine : IEngine{
    override fun start() {
        println("motor de gas encendido")
    }
}
class ElectricEngine : IEngine{
    override fun start() {
        println("motor electrico encendido")
    }
}
class Car(private val engine: IEngine) { // Depende de la interfaz, no de una clase concreta
    fun start() {
        engine.start()
    }
}

fun main() {
    val gasCar = Car(GasEngine())
    gasCar.start() // Motor de gasolina
    val electricCar = Car(ElectricEngine())
    electricCar.start() // Motor eléctrico
}