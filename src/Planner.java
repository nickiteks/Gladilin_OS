import java.util.Scanner;
import java.util.Random;

public class Planner {
    private Processor processor;
    private MemoryManager memoryManager;
    private Random random = new Random();

    public Planner() {
        processor = new Processor();
        memoryManager = new MemoryManager();
    }

    public void start() {
        System.out.println("�������� ��������: \n" +
                "���������� ��� �������� - p\n" +
                "������ - r\n" +
                "������ - w\n" +
                "����� ����� - s\n" +
                "��������� ��������� ������� - c\n" +
                "����� - q");

        Process[] processes = new Process[random.nextInt(5) + 2];
        try (Scanner scanner = new Scanner(System.in)) {
            for (Process process : processes) {
                process = new Process();
                process.setProcessName("�������: " + process.getId());
                process.fillVirtualMemory();
                Page.setGetId(-1);
                Page[] refToVirtualMemoryOfCurrentProcess = process.getVirtualMemory();
                processor.setVirtualMemoryOfCurrentProcess(refToVirtualMemoryOfCurrentProcess);
                processor.setMemoryManager(memoryManager);
                memoryManager.setVirtualMemoryOfCurrentProcess(refToVirtualMemoryOfCurrentProcess);
                boolean condition = true;
                while (condition) {
                    switch (scanner.next()) {
                        case "s":
                            System.out.println("����� ����� ��������� � ������");
                            memoryManager.resetAllBits();
                            break;
                        case "p":
                            System.out.println("����� ���� �������");
                            memoryManager.printVirtualMemoryOfCurrentProcess();
                            memoryManager.printPhysicalMemory();
                            memoryManager.printSwapMemory();
                            break;
                        case "r":
                            System.out.println("----������----");
                            processor.read(scanner.nextInt());
                            break;
                        case "w":
                            System.out.println("----������----");
                            processor.write(scanner.nextInt(), scanner.next());
                            break;
                        case "c":
                            condition = false;
                            break;
                        case "q":
                            return;
                    }
                }
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    public static void main(String[] args) {
        new Planner().start();
    }
}
