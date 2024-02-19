function [pares] = produtoCarteseano(conjuntoA, conjuntoB, conjuntoC)
    tamA = length(conjuntoA);
    tamB = length(conjuntoB);
    tamC = length(conjuntoC);
    
    par = [];
    pares = [];
    
    for i = 1:tamA
        for j = 1:tamB
            for k =1:tamC
                par = [conjuntoA(i), conjuntoB(j), conjuntoC(k)];
                pares = [pares; par];
            end
        end
    end
    
endfunction



