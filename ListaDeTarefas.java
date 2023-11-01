import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JScrollPane;
import javax.swing.JTextField;
import java.util.ArrayList;

public class ListaDeTarefas extends JFrame {
    private ArrayList<String> tarefas = new ArrayList<String>();
    private JTextField campoTarefa;
    private JButton botaoAdicionar;
    private JButton botaoRemover;
    private JButton botaoSair;
    private JButton botaoListar;
    private JList<String> listaTarefas;
    private int numeroTarefa = 1;

    public ListaDeTarefas() {
        super("Aplicativo de Lista de Tarefas");
        JPanel painelPrincipal = new JPanel();
        JPanel painelBotoes = new JPanel();

        JLabel labelTarefa = new JLabel("Tarefa:");
        campoTarefa = new JTextField(20);
        botaoAdicionar = new JButton("Adicionar");
        botaoRemover = new JButton("Remover Tarefa");
        botaoListar = new JButton("Listar Tarefas");
        botaoSair = new JButton("Sair");

        listaTarefas = new JList<>(tarefas.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(listaTarefas);

        painelBotoes.add(labelTarefa);
        painelBotoes.add(campoTarefa);
        painelBotoes.add(botaoAdicionar);
        painelBotoes.add(botaoRemover);
        painelBotoes.add(botaoListar);
        painelBotoes.add(botaoSair);

        botaoAdicionar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                adicionarTarefa("Tarefa " + numeroTarefa + ": " + campoTarefa.getText());
                campoTarefa.setText("");
                listaTarefas.setListData(tarefas.toArray(new String[0]));
                numeroTarefa++; // Aumenta o número da tarefa
            }
        });

        botaoRemover.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                if (!tarefas.isEmpty()) {
                    String selectedTarefa = listaTarefas.getSelectedValue();
                    if (selectedTarefa != null) {
                        removerTarefa(selectedTarefa);
                        renumerarTarefas();
                        listaTarefas.setListData(tarefas.toArray(new String[0]));
                    } else {
                        JOptionPane.showMessageDialog(ListaDeTarefas.this, "Selecione uma tarefa para remover.");
                    }
                } else {
                    JOptionPane.showMessageDialog(ListaDeTarefas.this, "Não há tarefas para remover.");
                }
            }
        });

        botaoListar.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                listarTarefas();
            }
        });

        botaoSair.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });

        painelPrincipal.setLayout(new BorderLayout());
        painelPrincipal.add(scrollPane, BorderLayout.CENTER);
        painelPrincipal.add(painelBotoes, BorderLayout.SOUTH);

        this.add(painelPrincipal);
        this.pack();
        this.setVisible(true);
        this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
    }

    private void adicionarTarefa(String tarefa) {
        tarefas.add(tarefa);
        System.out.println(tarefa);
    }

    private void removerTarefa(String tarefa) {
        if (tarefas.contains(tarefa)) {
            tarefas.remove(tarefa);
            System.out.println("Tarefa removida com sucesso.");
        } else {
            System.out.println("Tarefa não encontrada.");
        }
    }

    private void renumerarTarefas() {
        for (int i = 0; i < tarefas.size(); i++) {
            tarefas.set(i, "Tarefa " + (i + 1) + ": " + tarefas.get(i).substring(tarefas.get(i).indexOf(":") + 2));
        }
    }

    private void listarTarefas() {
        JFrame listaFrame = new JFrame("Lista de Tarefas");
        JList<String> lista = new JList<>(tarefas.toArray(new String[0]));
        JScrollPane scrollPane = new JScrollPane(lista);
        listaFrame.add(scrollPane);
        listaFrame.pack();
        listaFrame.setVisible(true);
    }

    public static void main(String[] args) {
        new ListaDeTarefas();
    }
}