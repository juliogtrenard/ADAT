"""Crea una clase Criptográfo que contenga dos métodos: encriptar y desencriptar. Los dos
métodos recibirán un texto y devolverán otro texto. El funcionamiento de los métodos es el
siguiente:
◦ encriptar(txt): El texto recibido se encriptará sustituyendo cada uno de los
caracteres por el siguiente caracter según su valor ASCII.
◦ desencriptar(txt): Realizará la acción inversa al metodo anterior, es decir sustituirá
cada carácter por el anterior según su valor ASCII.
"""

class Criptografo:
    def encriptar(txt):
            encriptado = []
            for i in range(len(txt)):
                caracter = txt[i]
                ascii = ord(caracter) + 1
                encriptado.append(ascii)
            
            return encriptado
        
    def desencriptar(txt):
            desencriptado = []
            for i in range(len(txt)):
                ascii = txt[i] - 1
                caracter = chr(ascii)
                desencriptado.append(caracter)
            
            return desencriptado