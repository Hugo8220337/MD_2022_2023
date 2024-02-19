clear
clc

b = 7; //beta
produtorio = 1;


for m = 1 : min((5 + b), ceil(100/(b+1)))
    produtorio = produtorio * ((3/(5*m)) - 1)^4;
end

disp(produtorio)


// ou


[minimo] = min((5 + b), ceil(100/(b+1)));
produtorio = prod(((3./(5.*(1:minimo))) - 1).^4);
disp(produtorio)


