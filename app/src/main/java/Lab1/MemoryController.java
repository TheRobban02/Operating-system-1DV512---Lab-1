package Lab1;

import java.io.IOException;
import java.lang.reflect.Array;
import java.util.ArrayList;

public class MemoryController {

    public List firstFit(MemoryIO memIO, List list) {

        try {
            memIO.load(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.addFirstBlock();
        int counter = 0;

        for (Job job : list.getJobList()) {
            if (job.getType().equals("A")) {
                firstFitStrategy(job, list);
            } else if (job.getType().equals("D")) {
                deAllocation(job, list);
            } else if (job.getType().equals("O")) {
                counter += 1;
                return list;
            }
        }

        return list;
    }

    public void firstFitStrategy(Job job, List mem) {
        for (Block b : mem.getMemoryList()) {
            if (b.getJob() == null && (b.getEndAdress() + 1) - b.getStartAdress() >= job.getSize()) {
                b.setJob(job);
                b.setEndAdress(b.getStartAdress() + job.getSize() - 1);
                Block newBlock = new Block(b.getEndAdress() + 1, mem.getMemorySize() - 1);

                if (newBlock.getEndAdress() - newBlock.getStartAdress() + 1 != 0) {
                    mem.addBlock(newBlock);
                    break;
                }

            }
        }
    }

    public void deAllocation(Job job, List list) {

        for (int i = 0; i < list.getMemoryList().size(); i++) {
            Block block = list.getMemoryList().get(i);
            if (block.getJob() != null) {
                if (block.getJob().getId() == job.getId()) {
                    if (i == 0) {
                        if (list.getMemoryList().size() != 1) {
                            Block nextBlock = list.getMemoryList().get(i + 1); // Slå ihop block om det är första
                                                                               // platsen som är
                            // blocket
                            if (nextBlock.getJob() == null) {
                                nextBlock.setStartAdress(block.getStartAdress());
                                list.removeBlock(block);
                            } else {
                                block.setJob(null);
                            }
                        } else {
                            block.setJob(null);
                        }

                    } else {
                        Block previusBlock = list.getMemoryList().get(i - 1); // Slå ihop blocken
                        if (previusBlock.getJob() == null) {
                            previusBlock.setEndAdress(block.getEndAdress());
                            list.removeBlock(block);
                        } else {
                            block.setJob(null);
                        }
                    }
                }
            }
        }
    }

    public double calculateFragmentation(List list) {

        double largestFreeMemoryblock = 0;
        double totalFreeMemory = 0;

        for (Block block : list.getMemoryList()) {
            if (block.getJob() == null) {
                double blockSize = (block.getEndAdress() + 1) - block.getStartAdress();
                if (blockSize > largestFreeMemoryblock) {
                    largestFreeMemoryblock = blockSize;
                }
                totalFreeMemory += blockSize;
            }
        }

        return (1 - (largestFreeMemoryblock / totalFreeMemory));
    }

    public List bestFit(MemoryIO memIO, List list) {

        try {
            memIO.load(list);
        } catch (IOException e) {
            e.printStackTrace();
        }

        list.addFirstBlock();
        int counter = 0;

        for (Job job : list.getJobList()) {
            if (job.getType().equals("A")) {
                bestFitStrategy(job, list);
            } else if (job.getType().equals("D")) {
                deAllocation(job, list);
            } else if (job.getType().equals("O")) {
                counter += 1;
                return list;
            }
        }

        return list;

    }

    public void bestFitStrategy(Job job, List list) {

        int[] blockSizes = new int[list.getMemoryList().size()];
        int targetsize = job.getSize();
        int temp = list.getMemorySize() + 1 - targetsize;
        int index = 0;

        for (int i = 0; i < list.getMemoryList().size(); i++) {
            blockSizes[i] = (list.getMemoryList().get(i).getEndAdress() + 1)
                    - list.getMemoryList().get(i).getStartAdress();
        }

        for (int i = 0; i < blockSizes.length; i++) {
            if (targetsize <= blockSizes[i] && blockSizes[i] - targetsize < temp) {
                temp = blockSizes[i] - targetsize;
                index = i;
            }
        }

        if (temp != list.getMemorySize() + 1 - targetsize) {
            list.getMemoryList().get(index).setJob(job);
            list.getMemoryList().get(index)
                    .setEndAdress(list.getMemoryList().get(index).getStartAdress() + job.getSize() - 1);

            Block newBlock = new Block(list.getMemoryList().get(index).getEndAdress() + 1, list.getMemorySize() - 1);

            if (newBlock.getEndAdress() - newBlock.getStartAdress() + 1 != 0) {
                list.addBlock(newBlock);
            }
        } 
    }
}
