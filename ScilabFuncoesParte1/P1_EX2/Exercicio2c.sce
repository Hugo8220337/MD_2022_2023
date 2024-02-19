clear
clc

b = 7; // beta
n = 30;

soma = 0;
prod = 1;

for k = 1 : (n-15)
    for j = (n - 5) : n
        soma = soma + (floor(1+((j+k)/(200)))-ceil(factorial(6)/b));
    end
    prod = prod * (3 * soma);
end

disp(prod)

