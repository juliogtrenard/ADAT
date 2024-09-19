#Crea una función que recorra un diccionario y busque si un elemento se
#encuentra en el mismo.

def buscar(elemento):
    for clave in diccionario.keys():
        if diccionario[clave] == elemento:
            return True        
    return False

diccionario = {123:'Coche', 456:'Perro', 789:'Avion'}

elemento = input("Introduce un elemento: ")

print(buscar(elemento))