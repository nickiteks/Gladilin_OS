
public class Processor {
    private Page[] virtualMemoryOfCurrentProcess;
    private MemoryManager memoryManager;

    public Processor() {
        virtualMemoryOfCurrentProcess = null;
    }

    public void setVirtualMemoryOfCurrentProcess(Page[] virtualMemoryOfCurrentProcess) {
        this.virtualMemoryOfCurrentProcess = virtualMemoryOfCurrentProcess;
    }

    public void read(int indexOfVirtualMemory) throws Exception {
        Page page = virtualMemoryOfCurrentProcess[indexOfVirtualMemory];
        if (page.getPresenceAndAbsenceBit() == 1) {
            if (page.getIndexAtPhysicalMemory() == -1) {
                memoryManager.setToPhysicalMemory(indexOfVirtualMemory);
            }
            page.setReadBit(1);
        }
        else {
            memoryManager.setToPhysicalMemory(indexOfVirtualMemory);
            read(indexOfVirtualMemory);
        }
    }
    public void write(int indexOfVirtualMemory, String description) throws Exception {
        Page page = virtualMemoryOfCurrentProcess[indexOfVirtualMemory];
        if (page.getPresenceAndAbsenceBit() == 1) {
            if (page.getIndexAtPhysicalMemory() == -1) {
                memoryManager.setToPhysicalMemory(indexOfVirtualMemory);
            }
            page.setModificationBit(1);
            page.setDescription(description);
        } else {
            memoryManager.setVirtualMemoryOfCurrentProcess(virtualMemoryOfCurrentProcess);
            memoryManager.setToPhysicalMemory(indexOfVirtualMemory);
            write(indexOfVirtualMemory, description);
        }
    }

    public void setMemoryManager(MemoryManager memoryManager) {
        this.memoryManager = memoryManager;
    }
}
