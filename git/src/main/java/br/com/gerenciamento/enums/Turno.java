package br.com.gerenciamento.enums;

public enum Turno {

    MATUTINO("Matutino"),
    NOTURNO("Noturno");

    @SuppressWarnings("unused")
    private String turno;

    private Turno(String turno) {
        this.turno = turno;
    }
}
