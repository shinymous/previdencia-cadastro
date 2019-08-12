package com.previdencia.cadastro.util;

import static java.util.Objects.isNull;

public class UtilCpf {

    public static boolean validaCpf(String cpf){

        if(isNull(cpf)){
            return false;
        }

        cpf = ajustaCpf(cpf);

        if (cpf.length() == 11 && !cpf.equals("00000000000") )
        {
            int     d1, d2;
            int     digito1, digito2, resto;
            int     digitoCPF;
            String  nDigResult;
            d1 = d2 = 0;
            digito1 = digito2 = resto = 0;
            for (int n_Count = 1; n_Count < cpf.length() -1; n_Count++)
            {
                digitoCPF = Integer.valueOf (cpf.substring(n_Count -1, n_Count)).intValue();
                //--------- Multiplique a ultima casa por 2 a seguinte por 3 a seguinte por 4 e assim por diante.
                d1 = d1 + ( 11 - n_Count ) * digitoCPF;
                //--------- Para o segundo digito repita o procedimento incluindo o primeiro digito calculado no passo anterior.
                d2 = d2 + ( 12 - n_Count ) * digitoCPF;
            };
            //--------- Primeiro resto da diviso por 11.
            resto = (d1 % 11);
            //--------- Se o resultado for 0 ou 1 o digito  0 caso contrrio o digito  11 menos o resultado anterior.
            if (resto < 2)
                digito1 = 0;
            else
                digito1 = 11 - resto;
            d2 += 2 * digito1;
            //--------- Segundo resto da diviso por 11.
            resto = (d2 % 11);
            //--------- Se o resultado for 0 ou 1 o digito  0 caso contrrio o digito  11 menos o resultado anterior.
            if (resto < 2)
                digito2 = 0;
            else
                digito2 = 11 - resto;
            //--------- Digito verificador do CPF que est sendo validado.
            String nDigVerific = cpf.substring (cpf.length()-2, cpf.length());
            //--------- Concatenando o primeiro resto com o segundo.
            nDigResult = String.valueOf(digito1) + String.valueOf(digito2);
            //--------- Comparar o digito verificador do cpf com o primeiro resto + o segundo resto.
            return nDigVerific.equals(nDigResult);
        }

        return false;
    }

    public static String ajustaCpf(String cpf){
        if(cpf != null && cpf.length() > 0 ){
            return cpf.replace(".", "").replace("/","").replace("-", "");
        }else {
            return cpf;
        }
    }
}
