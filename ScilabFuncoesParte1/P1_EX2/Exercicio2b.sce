clear
clc

b = 7; // beta
n = 30;
soma = 0;

for j = (7 + 3) : n
    soma = soma + ((-b-1)/5)^j;
end

disp(soma)

// ou

//n = 30;
soma = sum(((-7-1)/5)^(10:n));
disp(soma);

