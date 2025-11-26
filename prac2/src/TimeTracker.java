public class TimeTracker {
    private long start, parse, duplicates, floors, total;

    public void startAll() {
        start = System.currentTimeMillis();
    }
    public void endParse() {
        parse = System.currentTimeMillis() - start;
    }
    public void endDuplicates() {
        duplicates = System.currentTimeMillis() - start - parse;
    }
    public void endFloors() {
        floors = System.currentTimeMillis() - start - parse - duplicates;
    }
    public void endAll() {
        total = System.currentTimeMillis() - start;
    }
    public void printTime() {
        System.out.println("\n\tTime\n");
        System.out.println("file processing: " + parse + " ms");
        System.out.println("duplicate output: " + duplicates + " ms");
        System.out.println("floor output: " + floors + " ms");
        System.out.println("total program: " + total + " ms\n");
    }

}
