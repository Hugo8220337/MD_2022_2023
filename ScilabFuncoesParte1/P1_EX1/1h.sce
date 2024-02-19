function [pares] = produtoCarteseano(conjuntoA, conjuntoB)
    tamA = length(conjuntoA);
    tamB = length(conjuntoB);
    
    par = [];
    pares = [];
    
    for i = 1:tamA
        for j = 1:tamB
            par = [conjuntoA(i), conjuntoB(j)];
            pares = [pares; par];
        end
    end
    
endfunction


