
public class MemoryManager {
    public static final int RAM_SIZE = 32768;
    public static final int PAGE_SIZE = 4096;
    private Page[] physicalMemory;
    private Page[] swapMemory;
    private Page[] virtualMemoryOfCurrentProcess;

    public MemoryManager() {
        physicalMemory = new Page[RAM_SIZE / PAGE_SIZE];
        swapMemory = new Page[RAM_SIZE / PAGE_SIZE];
    }

    public void setToPhysicalMemory(int indexOfPage) throws Exception {
        int index;
        Page pageBlock = virtualMemoryOfCurrentProcess[indexOfPage];
        if (findInSwapMemory(pageBlock)) return;
        index = getFreeIndex(physicalMemory);
        if (index != -1) {
            physicalMemory[index] = pageBlock;
        } else {
            index = swapProcess();
            if (index == -1) {
                System.out.println("Нет места в ОЗУ и в файле подкачки");
                throw new Exception();
            }
            physicalMemory[index] = pageBlock;
        }
        pageBlock.setPresenceAndAbsenceBit(1);
        pageBlock.setIndexAtPhysicalMemory(index);
        pageBlock.setIndexAtSwapMemory(-1);
        pageBlock.setDescription("loadedToRAM");
    }

    private boolean findInSwapMemory(Page pageBlock) {
        int index;
        for (int i = 0; i < swapMemory.length; i++) {
            if (swapMemory[i] == pageBlock) {
                index = getFreeIndex(physicalMemory);
                if (index == -1) {
                    index = swapProcess();
                    initPageInPhysicalMemory(pageBlock, index);
                    swapMemory[i] = null;
                    return true;
                } else {
                    initPageInPhysicalMemory(pageBlock, index);
                    swapMemory[i] = null;
                    return true;
                }
            }
        }
        return false;
    }

    private void initPageInPhysicalMemory(Page pageBlock, int index) {
        physicalMemory[index] = pageBlock;
        pageBlock.setPresenceAndAbsenceBit(1);
        pageBlock.setIndexAtPhysicalMemory(index);
        pageBlock.setIndexAtSwapMemory(-1);
        pageBlock.setDescription("loadedToRAM");
    }

    private int getFreeIndex(Page[] memory) {
        for (int i = 0; i < memory.length; i++) {
            if (memory[i] == null) {
                return i;
            }
        }
        return -1;
    }

    private int swapProcess() {
        for (int i = 0; i < physicalMemory.length; i++) {
            Page page = physicalMemory[i];
            if (page.getModificationBit() == 0 && page.getReadBit() == 0) {
                swap(page);
                return i;
            }
        }
        for (int i = 0; i < physicalMemory.length; i++) {
            Page page = physicalMemory[i];
            if (page.getModificationBit() == 1 && page.getReadBit() == 0) {
                swap(page);
                return i;
            }
        }
        for (int i = 0; i < physicalMemory.length; i++) {
            Page page = physicalMemory[i];
            if (page.getModificationBit() == 0 && page.getReadBit() == 1) {
                swap(page);
                return i;
            }
        }
        for (int i = 0; i < physicalMemory.length; i++) {
            Page page = physicalMemory[i];
            if (page.getModificationBit() == 1 && page.getReadBit() == 1) {
                swap(page);
                return i;
            }
        }
        return -1;
    }

    private void swap(Page page) {
        int index = getFreeIndex(swapMemory);
        swapMemory[index] = page;
        page.setIndexAtSwapMemory(index);
        page.setIndexAtPhysicalMemory(-1);
        page.setReadBit(0);
        page.setModificationBit(0);
        page.setPresenceAndAbsenceBit(0);
        page.setDescription("loadedToSwap");
    }

    public void setVirtualMemoryOfCurrentProcess(Page[] virtualMemoryOfCurrentProcess) {
        this.virtualMemoryOfCurrentProcess = virtualMemoryOfCurrentProcess;
    }

    public void printPhysicalMemory() {
        System.out.println("Содержимое физической памяти:");
        for (Page page : physicalMemory) {
            System.out.println(page);
        }
    }

    public void printSwapMemory() {
        System.out.println("Содержимое swap:");
        for (Page page : swapMemory) {
            System.out.println(page);
        }
    }

    public void printVirtualMemoryOfCurrentProcess() {
        System.out.println("Содержимое виртуальной памяти");
        for (Page memoryOfCurrentProcess : virtualMemoryOfCurrentProcess) {
            System.out.println(memoryOfCurrentProcess);
        }
    }

    public void resetAllBits() {
        for (int i = 0; i < physicalMemory.length; i++) {
            Page page = physicalMemory[i];
            if (page != null) {
                page.setModificationBit(0);
                page.setReadBit(0);
            }
        }
    }
}
