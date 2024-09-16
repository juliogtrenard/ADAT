#Escribe un programa que devuelva True cuando una palabra no contenga
#‘a’ y false en caso contrario.

def contiene(palabra, letra):
    contiene = False
    for i in range(len(palabra)):
        if palabra[i] == letra:
            contiene = True

    return contiene

pal = input("Escribe la palabra: ")

print(contiene(pal, "a"))