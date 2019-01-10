let funciones = {}

funciones.validarCedula = function (ced) {
  let cedula = ced
  if (cedula.length === 10) {
    let digitoRegion = cedula.substring(0, 2)
    if (digitoRegion >= 1 && digitoRegion <= 24) {
      let ultimoDigito = cedula.substring(9, 10)
      let pares = parseInt(cedula.substring(1, 2)) + parseInt(cedula.substring(3, 4)) + parseInt(cedula.substring(5, 6)) + parseInt(cedula.substring(7, 8))
      let numero1 = cedula.substring(0, 1)
      numero1 = (numero1 * 2)
      if (numero1 > 9) { numero1 = (numero1 - 9) }
      let numero3 = cedula.substring(2, 3)
      numero3 = (numero3 * 2)
      if (numero3 > 9) { numero3 = (numero3 - 9) }
      let numero5 = cedula.substring(4, 5)
      numero5 = (numero5 * 2)
      if (numero5 > 9) { numero5 = (numero5 - 9) }
      let numero7 = cedula.substring(6, 7)
      numero7 = (numero7 * 2)
      if (numero7 > 9) { numero7 = (numero7 - 9) }
      let numero9 = cedula.substring(8, 9)
      numero9 = (numero9 * 2)
      if (numero9 > 9) { numero9 = (numero9 - 9) }
      let impares = numero1 + numero3 + numero5 + numero7 + numero9

      let sumaTotal = (pares + impares)
      let primerDigitoSuma = String(sumaTotal).substring(0, 1)
      let decena = (parseInt(primerDigitoSuma) + 1) * 10
      let digitoValidador = decena - sumaTotal
      if (digitoValidador === 10) {
        digitoValidador = 0
      }
      if (digitoValidador.toString() === ultimoDigito.toString()) {
        return true
      } else {
        return false
      }
    } else {
      return false
    }
  } else {
    return false
  }
}

module.exports = funciones
