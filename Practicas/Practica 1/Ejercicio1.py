import os
import re

#Ruta en la que se encuentra el script.
ruta_actual = os.path.dirname(os.path.abspath(__file__))

#'palabras.txt' y 'cifradas.txt', siguiendo la ruta actual
ruta_palabras = os.path.join(ruta_actual, 'palabras.txt')
ruta_cifradas = os.path.join(ruta_actual, 'cifradas.txt')

def cifrar_fichero():
    #Se abren y se escriben/leen datos
    with open(ruta_palabras, 'r') as f_entrada, open(ruta_cifradas, 'w') as f_salida:
        for linea in f_entrada:
            texto = linea.strip()
            if not texto:
                continue
            print(f"Palabra a cifrar: {texto}")
            texto = texto.upper()
            texto = re.sub(r'[^A-Za-z]', '', texto)

            #Pedir la clave
            clave = input(f"Dame clave para cifrar (tiene que tener el mismo largo que '{texto}'): ").upper()
            #Comprobación que la clave tiene el mismo largo que la palabra
            while len(clave) != len(texto):
                clave = input(f"La clave debe tener el mismo largo que '{texto}', intenta de nuevo: ").upper()

            # Cifrar el texto
            texto_cifrado = ''
            for i in range(len(texto)):
                posicion_texto = ord(texto[i]) - ord('A') #Posición de la letra actual de la palabra
                posicion_clave = ord(clave[i]) - ord('A') #Posición de la letra actual de la clave
                posicion_cifrada = (posicion_texto + posicion_clave) % 26 #Calcula que siempre sea un numero entre 1 y 26 sin incluir
                letra_cifrada = chr(posicion_cifrada + ord('A')) #La posición de la letra cifrada
                texto_cifrado += letra_cifrada #Concatena la letra cifrada al contenido del texto cifrado

            f_salida.write(texto_cifrado + '\n') #Palabra cifrada

    print("Cifrado completado")

def descifrar_fichero():
    ruta_desencriptadas = os.path.join(ruta_actual, 'desencriptadas.txt')
    #Se abren y se escriben/leen datos
    with open(ruta_cifradas, 'r') as f_entrada, open(ruta_desencriptadas, 'w') as f_salida:
        for linea in f_entrada:
            texto_cifrado = linea.strip()
            if not texto_cifrado:
                continue
            print(f"Palabra cifrada a descifrar: {texto_cifrado}")
            texto_cifrado = texto_cifrado.upper()
            clave = input(f"Dame clave para descifrar (tiene que tener el mismo largo que '{texto_cifrado}'): ").upper()

            while len(clave) != len(texto_cifrado):
                clave = input(f"La clave debe tener el mismo largo que '{texto_cifrado}', intenta de nuevo: ").upper()

            texto_descifrado = ''

            for i in range(len(texto_cifrado)):
                pos_cifrada = ord(texto_cifrado[i]) - ord('A')
                pos_clave = ord(clave[i]) - ord('A')
                pos_texto = (pos_cifrada - pos_clave) % 26 #Resta la posición para sacar la palabra original
                letra_texto = chr(pos_texto + ord('A'))
                texto_descifrado += letra_texto

            f_salida.write(texto_descifrado + '\n') #la escribe en desencriptadas.txt

    print("Descifrado completado")

cifrar_fichero()
descifrar_fichero()