class ArvoreVermelhaPreta {
    private NoPV raiz;
    private final boolean VERMELHO = true;
    private final boolean PRETO = false;

    public void inserir(int valor) {
        NoPV novoNo = new NoPV(valor);
        raiz = inserirNo(raiz, novoNo);
        balancearInsercao(novoNo);
    }

    private NoPV inserirNo(NoPV raiz, NoPV novoNo) {
        if (raiz == null)
            return novoNo;
        if (novoNo.getValor() < raiz.getValor()) {
            raiz.setEsquerda(inserirNo(raiz.getEsquerda(), novoNo));
            raiz.getEsquerda().setPai(raiz);
        } else if (novoNo.getValor() > raiz.getValor()) {
            raiz.setDireita(inserirNo(raiz.getDireita(), novoNo));
            raiz.getDireita().setPai(raiz);
        }
        return raiz;
    }

    public void remover(int valor) {
        NoPV noParaRemover = buscarNo(raiz, valor);
        if (noParaRemover == null)
            return;

        NoPV substituto = noParaRemover;
        boolean corOriginal = substituto.isCor();
        NoPV filho;

        if (noParaRemover.getEsquerda() == null) {
            filho = noParaRemover.getDireita();
            transplantar(noParaRemover, noParaRemover.getDireita());
        } else if (noParaRemover.getDireita() == null) {
            filho = noParaRemover.getEsquerda();
            transplantar(noParaRemover, noParaRemover.getEsquerda());
        } else {
            substituto = menorValor(noParaRemover.getDireita());
            corOriginal = substituto.isCor();
            filho = substituto.getDireita();
            if (substituto.getPai() == noParaRemover) {
                if (filho != null)
                    filho.setPai(substituto);
            } else {
                transplantar(substituto, substituto.getDireita());
                substituto.setDireita(noParaRemover.getDireita());
                substituto.getDireita().setPai(substituto);
            }
            transplantar(noParaRemover, substituto);
            substituto.setEsquerda(noParaRemover.getEsquerda());
            substituto.getEsquerda().setPai(substituto);
            substituto.setCor(noParaRemover.isCor());
        }

        if (corOriginal == PRETO) {
            balancearRemocao(filho);
        }
    }

    private void transplantar(NoPV antigoNo, NoPV novoNo) {
        if (antigoNo.getPai() == null) {
            raiz = novoNo;
        } else if (antigoNo == antigoNo.getPai().getEsquerda()) {
            antigoNo.getPai().setEsquerda(novoNo);
        } else {
            antigoNo.getPai().setDireita(novoNo);
        }
        if (novoNo != null)
            novoNo.setPai(antigoNo.getPai());
    }

    private NoPV buscarNo(NoPV raiz, int valor) {
        while (raiz != null && valor != raiz.getValor()) {
            if (valor < raiz.getValor())
                raiz = raiz.getEsquerda();
            else
                raiz = raiz.getDireita();
        }
        return raiz;
    }

    private NoPV menorValor(NoPV no) {
        while (no.getEsquerda() != null)
            no = no.getEsquerda();
        return no;
    }

    private void balancearInsercao(NoPV no) {
        while (no != raiz && no.getPai().isCor() == VERMELHO) {
            if (no.getPai() == no.getPai().getPai().getEsquerda()) {
                NoPV tio = no.getPai().getPai().getDireita();
                if (tio != null && tio.isCor() == VERMELHO) {
                    no.getPai().setCor(PRETO);
                    tio.setCor(PRETO);
                    no.getPai().getPai().setCor(VERMELHO);
                    no = no.getPai().getPai();
                } else {
                    if (no == no.getPai().getDireita()) {
                        no = no.getPai();
                        rotacaoEsquerda(no);
                    }
                    no.getPai().setCor(PRETO);
                    no.getPai().getPai().setCor(VERMELHO);
                    rotacaoDireita(no.getPai().getPai());
                }
            } else {
                NoPV tio = no.getPai().getPai().getEsquerda();
                if (tio != null && tio.isCor() == VERMELHO) {
                    no.getPai().setCor(PRETO);
                    tio.setCor(PRETO);
                    no.getPai().getPai().setCor(VERMELHO);
                    no = no.getPai().getPai();
                } else {
                    if (no == no.getPai().getEsquerda()) {
                        no = no.getPai();
                        rotacaoDireita(no);
                    }
                    no.getPai().setCor(PRETO);
                    no.getPai().getPai().setCor(VERMELHO);
                    rotacaoEsquerda(no.getPai().getPai());
                }
            }
        }
        raiz.setCor(PRETO);
    }

    private void balancearRemocao(NoPV no) {
        while (no != raiz && (no == null || no.isCor() == PRETO)) {
            if (no == no.getPai().getEsquerda()) {
                NoPV irmao = no.getPai().getDireita();
                if (irmao.isCor() == VERMELHO) {
                    irmao.setCor(PRETO);
                    no.getPai().setCor(VERMELHO);
                    rotacaoEsquerda(no.getPai());
                    irmao = no.getPai().getDireita();
                }
                if ((irmao.getEsquerda() == null || irmao.getEsquerda().isCor() == PRETO) &&
                        (irmao.getDireita() == null || irmao.getDireita().isCor() == PRETO)) {
                    irmao.setCor(VERMELHO);
                    no = no.getPai();
                } else {
                    if (irmao.getDireita() == null || irmao.getDireita().isCor() == PRETO) {
                        if (irmao.getEsquerda() != null)
                            irmao.getEsquerda().setCor(PRETO);
                        irmao.setCor(VERMELHO);
                        rotacaoDireita(irmao);
                        irmao = no.getPai().getDireita();
                    }
                    irmao.setCor(no.getPai().isCor());
                    no.getPai().setCor(PRETO);
                    if (irmao.getDireita() != null)
                        irmao.getDireita().setCor(PRETO);
                    rotacaoEsquerda(no.getPai());
                    no = raiz;
                }
            } else {
                NoPV irmao = no.getPai().getEsquerda();
                if (irmao.isCor() == VERMELHO) {
                    irmao.setCor(PRETO);
                    no.getPai().setCor(VERMELHO);
                    rotacaoDireita(no.getPai());
                    irmao = no.getPai().getEsquerda();
                }
                if ((irmao.getEsquerda() == null || irmao.getEsquerda().isCor() == PRETO) &&
                        (irmao.getDireita() == null || irmao.getDireita().isCor() == PRETO)) {
                    irmao.setCor(VERMELHO);
                    no = no.getPai();
                } else {
                    if (irmao.getEsquerda() == null || irmao.getEsquerda().isCor() == PRETO) {
                        if (irmao.getDireita() != null)
                            irmao.getDireita().setCor(PRETO);
                        irmao.setCor(VERMELHO);
                        rotacaoEsquerda(irmao);
                        irmao = no.getPai().getEsquerda();
                    }
                    irmao.setCor(no.getPai().isCor());
                    no.getPai().setCor(PRETO);
                    if (irmao.getEsquerda() != null)
                        irmao.getEsquerda().setCor(PRETO);
                    rotacaoDireita(no.getPai());
                    no = raiz;
                }
            }
        }
        if (no != null)
            no.setCor(PRETO);
    }

    private void rotacaoEsquerda(NoPV no) {
        NoPV direita = no.getDireita();
        no.setDireita(direita.getEsquerda());
        if (direita.getEsquerda() != null)
            direita.getEsquerda().setPai(no);
        direita.setPai(no.getPai());
        if (no.getPai() == null)
            raiz = direita;
        else if (no == no.getPai().getEsquerda())
            no.getPai().setEsquerda(direita);
        else
            no.getPai().setDireita(direita);
        direita.setEsquerda(no);
        no.setPai(direita);
    }

    private void rotacaoDireita(NoPV no) {
        NoPV esquerda = no.getEsquerda();
        no.setEsquerda(esquerda.getDireita());
        if (esquerda.getDireita() != null)
            esquerda.getDireita().setPai(no);
        esquerda.setPai(no.getPai());
        if (no.getPai() == null)
            raiz = esquerda;
        else if (no == no.getPai().getDireita())
            no.getPai().setDireita(esquerda);
        else
            no.getPai().setEsquerda(esquerda);
        esquerda.setDireita(no);
        no.setPai(esquerda);
    }

    public void exibir() {
        exibirArvore(raiz);
        System.out.println();
    }

    private void exibirArvore(NoPV no) {
        if (no != null) {
            exibirArvore(no.getEsquerda());
            System.out.print(no.getValor() + " ");
            exibirArvore(no.getDireita());
        }
    }
}
