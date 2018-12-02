package agh.java.project;

import java.util.LinkedList;
import java.util.List;
import java.util.ListIterator;
import java.util.Objects;

public class Judge{
    private String name;
    private String function;
    private LinkedList<JudgeRole> specialRoles;

    public String toString() {
        StringBuilder sb = new StringBuilder();
        if(function != null) sb.append(function).append(" ");
        sb.append(name);
        if(specialRoles.size() > 0) sb.append(" - ");
        ListIterator<JudgeRole> iter = specialRoles.listIterator();
        while(iter.hasNext()) {
            JudgeRole currentRole = iter.next();
            if(currentRole != null) {
                sb.append(currentRole);
                if(iter.hasNext()) sb.append(", ");
            }
        }
        return sb.toString();
    }

    public String getName() {
        return name;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (!(o instanceof Judge)) return false;
        Judge judge = (Judge) o;
        return name.equals(judge.name);
    }

    @Override
    public int hashCode() {
        return Objects.hash(name);
    }

}
