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
        System.out.println("Выберите действие: \n" +
                "Напечатать все страницы - p\n" +
                "Чтение - r\n" +
                "Запись - w\n" +
                "Сброс битов - s\n" +
                "Запустить следующий процесс - c\n" +
                "Выход - q");

        Process[] processes = new Process[random.nextInt(5) + 2];
        try (Scanner scanner = new Scanner(System.in)) {
            for (Process process : processes) {
                process = new Process();
                process.setProcessName("Процесс: " + process.getId());
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
                            System.out.println("Сброс битов изменения и чтения");
                            memoryManager.resetAllBits();
                            break;
                        case "p":
                            System.out.println("Вывод всех страниц");
                            memoryManager.printVirtualMemoryOfCurrentProcess();
                            memoryManager.printPhysicalMemory();
                            memoryManager.printSwapMemory();
                            break;
                        case "r":
                            System.out.println("----Чтение----");
                            processor.read(scanner.nextInt());
                            break;
                        case "w":
                            System.out.println("----Запись----");
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
