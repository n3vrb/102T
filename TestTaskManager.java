import java.util.List;

public class TestTaskManager {
    public static void main(String[] args) {

        System.out.println("=== TASK MANAGEMENT SYSTEM TEST ===");
        System.out.println();

        // --- Test 1: Create tasks ---
        System.out.println("--- Test 1: Creating Tasks ---");
        try {
            CodingTask ct1 = new CodingTask("T001", "Build Login API", 2, "Java");
            CodingTask ct2 = new CodingTask("T002", "Fix Payment Bug", 1, "Python");
            DocumentationTask dt1 = new DocumentationTask("T003", "Write User Guide", 3, 45);
            DocumentationTask dt2 = new DocumentationTask("T004", "API Reference", 1, 120);
            CodingTask ct3 = new CodingTask("T005", "Database Migration", 4, "Java");

            System.out.println(ct1);
            System.out.println(ct2);
            System.out.println(dt1);
            System.out.println(dt2);
            System.out.println(ct3);

            // --- Test 2: Invalid task ---
            System.out.println();
            System.out.println("--- Test 2: Invalid Task (Checked Exception) ---");
            try {
                CodingTask bad = new CodingTask("T099", "Bad Task", 7, "Go");
            } catch (InvalidTaskException e) {
                System.out.println("Caught: " + e.getMessage());
            }

            // --- Test 3: TaskProcessor ---
            System.out.println();
            System.out.println("--- Test 3: Adding Tasks to Processor ---");
            TaskProcessor processor = new TaskProcessor(5);
            processor.addTask(ct1);
            processor.addTask(ct2);
            processor.addTask(dt1);
            processor.addTask(dt2);
            processor.addTask(ct3);
            System.out.println("Tasks added: " + processor.getTaskList().size());
            System.out.println("Queue size: " + processor.getQueueSize());

            // --- Test 4: Overflow (Runtime Exception) ---
            System.out.println();
            System.out.println("--- Test 4: Task Overflow (Runtime Exception) ---");
            try {
                CodingTask extra = new CodingTask("T006", "Extra Task", 2, "C++");
                processor.addTask(extra);
            } catch (TaskOverflowException e) {
                System.out.println("Caught: " + e.getMessage());
            }

            // --- Test 5: Duplicate ID ---
            System.out.println();
            System.out.println("--- Test 5: Duplicate Task ID ---");
            try {
                CodingTask dup = new CodingTask("T001", "Duplicate", 2, "Ruby");
                processor.addTask(dup);
            } catch (IllegalArgumentException e) {
                System.out.println("Caught: " + e.getMessage());
            }

            // --- Test 6: Sorted tasks ---
            System.out.println();
            System.out.println("--- Test 6: Tasks Sorted by Priority ---");
            List<Task> sorted = processor.getSortedTasks();
            for (Task t : sorted) {
                System.out.println("  " + t);
            }

            // --- Test 7: Process queue ---
            System.out.println();
            System.out.println("--- Test 7: Processing Queue (FIFO) ---");
            Task processed1 = processor.processNextTask();
            Task processed2 = processor.processNextTask();
            System.out.println("Processed: " + processed1.getId());
            System.out.println("Processed: " + processed2.getId());
            System.out.println("Remaining in queue: " + processor.getQueueSize());

            // --- Test 8: Undo ---
            System.out.println();
            System.out.println("--- Test 8: Undo Last Process ---");
            Task undone = processor.undoLastProcess();
            System.out.println("Undone: " + undone.getId());
            System.out.println("Stack size after undo: " + processor.getStackSize());

            // --- Test 9: Find by ID ---
            System.out.println();
            System.out.println("--- Test 9: Find Task by ID (Map) ---");
            Task found = processor.findTaskById("T003");
            System.out.println("Found: " + found);
            Task notFound = processor.findTaskById("T999");
            System.out.println("Not found: " + notFound);

            // --- Test 10: Categories ---
            System.out.println();
            System.out.println("--- Test 10: Unique Categories (Set) ---");
            System.out.println("Categories: " + processor.getCategories());

            // --- Test 11: equals ---
            System.out.println();
            System.out.println("--- Test 11: Task Equality ---");
            CodingTask eqTest = new CodingTask("T001", "Different Name", 5, "Rust");
            System.out.println("T001 equals T001 (diff name): " + ct1.equals(eqTest));
            System.out.println("T001 equals T002: " + ct1.equals(ct2));

        } catch (InvalidTaskException e) {
            System.out.println("Unexpected error: " + e.getMessage());
        }

        System.out.println();
        System.out.println("=== ALL TESTS COMPLETE ===");
    }
}
