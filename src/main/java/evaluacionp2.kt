fun obtenerCategoria(puntos: Int): String {
    return when {
        puntos >= 1000 -> "Leyenda"
        puntos >= 500 -> "Experto"
        puntos >= 200 -> "Competidor"
        else -> "Novato"
    }
}

fun buscarParticipante(participantes: MutableList<String>, nombre: String): Int {
    for (i in 0..participantes.size - 1) {
        if (participantes[i] == nombre) {
            return i
        }
    }

    return -1
}

fun mostrarMenu() {
    println()
    println("===== TORNEO DE VIDEOJUEGOS =====")
    println("1. Registrar participante")
    println("2. Registrar puntos")
    println("3. Consultar participante")
    println("4. Mostrar estadisticas del torneo")
    println("5. Finalizar programa")
    print("Seleccione una opcion: ")
}

fun registrarParticipante(participantes: MutableList<String>, puntos: MutableList<Int>) {
    print("Ingrese el nombre del participante: ")
    val nombre = readln()

    if (nombre == "") {
        println("No se puede registrar un nombre vacio.")
    } else {
        val posicion = buscarParticipante(participantes, nombre)

        if (posicion != -1) {
            println("Ese participante ya esta registrado.")
        } else {
            participantes.add(nombre)
            puntos.add(0)
            println("Participante registrado correctamente.")
        }
    }
}

fun registrarPuntos(participantes: MutableList<String>, puntos: MutableList<Int>) {
    if (participantes.size == 0) {
        println("Primero debes registrar participantes.")
        return
    }

    print("Ingrese el nombre del participante: ")
    val nombre = readln()

    val posicion = buscarParticipante(participantes, nombre)

    if (posicion == -1) {
        println("El participante no existe.")
    } else {
        print("Ingrese los puntos obtenidos: ")
        val nuevosPuntos = readln().toInt()

        if (nuevosPuntos <= 0) {
            println("Los puntos deben ser mayores que cero.")
        } else {
            puntos[posicion] = puntos[posicion] + nuevosPuntos
            println("Puntos registrados correctamente.")
        }
    }
}

fun consultarParticipante(participantes: MutableList<String>, puntos: MutableList<Int>) {
    if (participantes.size == 0) {
        println("No hay participantes registrados.")
        return
    }

    print("Ingrese el nombre del participante a consultar: ")
    val nombre = readln()

    val posicion = buscarParticipante(participantes, nombre)

    if (posicion == -1) {
        println("El participante no fue encontrado.")
    } else {
        println()
        println("Nombre: ${participantes[posicion]}")
        println("Puntos acumulados: ${puntos[posicion]}")
        println("Categoria: ${obtenerCategoria(puntos[posicion])}")
    }
}

fun mostrarEstadisticas(participantes: MutableList<String>, puntos: MutableList<Int>) {
    if (participantes.size == 0) {
        println("No hay participantes para mostrar estadisticas.")
        return
    }

    var totalPuntos = 0
    var mayor = puntos[0]
    var menor = puntos[0]
    var participanteMayor = participantes[0]
    var participanteMenor = participantes[0]

    var leyenda = 0
    var experto = 0
    var competidor = 0
    var novato = 0

    for (i in 0..participantes.size - 1) {
        totalPuntos = totalPuntos + puntos[i]

        if (puntos[i] > mayor) {
            mayor = puntos[i]
            participanteMayor = participantes[i]
        }

        if (puntos[i] < menor) {
            menor = puntos[i]
            participanteMenor = participantes[i]
        }

        when (obtenerCategoria(puntos[i])) {
            "Leyenda" -> leyenda = leyenda + 1
            "Experto" -> experto = experto + 1
            "Competidor" -> competidor = competidor + 1
            "Novato" -> novato = novato + 1
        }
    }

    val promedio = totalPuntos.toDouble() / participantes.size

    println()
    println("===== ESTADISTICAS DEL TORNEO =====")
    println("Cantidad total de participantes: ${participantes.size}")
    println("Total de puntos acumulados: $totalPuntos")
    println("Promedio de puntos por participante: $promedio")
    println("Participante con mayor cantidad de puntos: $participanteMayor con $mayor puntos")
    println("Participante con menor cantidad de puntos: $participanteMenor con $menor puntos")
    println()
    println("Cantidad por categoria:")
    println("Leyenda: $leyenda")
    println("Experto: $experto")
    println("Competidor: $competidor")
    println("Novato: $novato")
}

fun main() {
    val participantes = mutableListOf<String>()
    val puntos = mutableListOf<Int>()

    var opcion: Int

    do {
        mostrarMenu()
        opcion = readln().toInt()

        when (opcion) {
            1 -> registrarParticipante(participantes, puntos)
            2 -> registrarPuntos(participantes, puntos)
            3 -> consultarParticipante(participantes, puntos)
            4 -> mostrarEstadisticas(participantes, puntos)
            5 -> println("Programa finalizado.")
            else -> println("Opcion invalida.")
        }

    } while (opcion != 5)
}