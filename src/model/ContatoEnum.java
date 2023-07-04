package model;

public enum ContatoEnum {
    INSTAGRAM,
    TWITTER,
    FACEBOOK,
    YOUTUBE;

    public static ContatoEnum getTipoById(Integer opcao) {
        if (opcao == 1) {
            return INSTAGRAM;
        } else if (opcao == 2) {
            return TWITTER;
        }else if (opcao == 3) {
            return FACEBOOK;
        } else {
            return YOUTUBE;
        }
    }
}
