#Escribe un programa que pase números decimales a binarios.

numero_decimal = int(input("Escribe el número decimal "))

modulos = []
while numero_decimal != 0:
    modulos.insert(0, numero_decimal % 2)
    numero_decimal //= 2

print(modulos)