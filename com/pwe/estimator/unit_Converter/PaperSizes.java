package com.pwe.estimator.unit_Converter;

import java.util.List;

import static java.util.Arrays.asList;
import static java.util.stream.Collectors.toList;

public class PaperSizes extends UnitUtils {
    private final double size_y;
    private final double size_x;
    private final Type type;

    PaperSizes(double size_x, double size_y, Type type) {
        this.size_x = size_x;
        this.size_y = size_y;
        this.type = type;
    }

    public double getSize_x() {
        return size_x;
    }

    public double getSize_y() {
        return size_y;
    }

    public Type getType() {
        return type;
    }


    private static final List<PaperSizes> paper = asList(
            new PaperSizes(paperSize[0][0], paperSize[0][1], Type.WORK),
            new PaperSizes(paperSize[1][0], paperSize[1][1], Type.WORK),
            new PaperSizes(paperSize[2][0], paperSize[2][1], Type.WORK),
            new PaperSizes(paperSize[3][0], paperSize[3][1], Type.WORK),
            new PaperSizes(paperSize[4][0], paperSize[4][1], Type.WORK),
            new PaperSizes(paperSize[5][0], paperSize[5][1], Type.WORK),
            new PaperSizes(paperSize[6][0], paperSize[6][1], Type.WORK),
            new PaperSizes(machineSize[0][0], machineSize[0][1], Type.MACHINE),
            new PaperSizes(machineSize[1][0], machineSize[1][1], Type.MACHINE),
            new PaperSizes(machineSize[2][0], machineSize[2][1], Type.MACHINE),
            new PaperSizes(machineSize[3][0], machineSize[3][1], Type.MACHINE),
            new PaperSizes(machineSize[4][0], machineSize[4][1], Type.MACHINE),
            new PaperSizes(machineSize[5][0], machineSize[5][1], Type.MACHINE)
    );


    public static List<Double> getUnit(String s, Type type, char x) {
        return x != 'x' ? getUnit_y(s, type) : getUnit_x(s, type);
    }

    private static List<Double> getUnit_x(String s, Type type) {
        if (s.equals(unitNameString[3])) {
            return paper
                    .parallelStream()
                    .filter(paper1 -> paper1.getType() == type)
                    .map(paperSizes -> paperSizes.getSize_x() * unitDoubles[7])
                    .collect(toList());
        } else if (s.equals(unitNameString[2])) {
            return paper
                    .parallelStream()
                    .filter(paper1 -> paper1.getType() == type)
                    .map(paperSizes -> paperSizes.getSize_x() * unitDoubles[8])
                    .collect(toList());
        } else if (s.equals(unitNameString[1])) {
            return paper
                    .parallelStream()
                    .filter(paper1 -> paper1.getType() == type)
                    .map(paperSizes -> paperSizes.getSize_x() * unitDoubles[6])
                    .collect(toList());
        } else
            return paper
                    .parallelStream()
                    .filter(paper1 -> paper1.getType() == type)
                    .map(PaperSizes::getSize_x)
                    .collect(toList());
    }

    private static List<Double> getUnit_y(String s, Type type) {

        if (s.equals(unitNameString[3])) {
            return paper
                    .parallelStream()
                    .filter(paper1 -> paper1.getType() == type)
                    .map(paperSizes -> paperSizes.getSize_y() * unitDoubles[7])
                    .collect(toList());
        } else if (s.equals(unitNameString[2])) {
            return paper
                    .parallelStream()
                    .filter(paper1 -> paper1.getType() == type)
                    .map(paperSizes -> paperSizes.getSize_y() * unitDoubles[8])
                    .collect(toList());
        } else if (s.equals(unitNameString[1])) {
            return paper
                    .parallelStream()
                    .filter(paper1 -> paper1.getType() == type)
                    .map(paperSizes -> paperSizes.getSize_y() * unitDoubles[6])
                    .collect(toList());
        } else
            return paper
                    .parallelStream()
                    .filter(paper1 -> paper1.getType() == type)
                    .map(PaperSizes::getSize_y)
                    .collect(toList());
    }
}
