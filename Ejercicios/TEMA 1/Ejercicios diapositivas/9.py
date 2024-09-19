#Crea un diccionario que contenga los caracteres ascii y su
#correspondiente valor numérico

diccionario_ascii = {}

cant = int(input("Cantidad de caracteres: "))

for i in range(cant):
    diccionario_ascii[i] = chr(i)
print(diccionario_ascii)