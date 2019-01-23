package fr.tse.fise3.pri.p002.server.pojo;

import java.util.List;

public class HalApiResponseEntity {


    private long numFound;
    private long start;
    private List<HalApiDoc> docs;


    public HalApiResponseEntity() {
    }

    public HalApiResponseEntity(long numFound, long start, List<HalApiDoc> docs) {
        this.numFound = numFound;
        this.start = start;
        this.docs = docs;
    }

    public long getNumFound() {
        return numFound;
    }

    public void setNumFound(long numFound) {
        this.numFound = numFound;
    }

    public long getStart() {
        return start;
    }

    public void setStart(long start) {
        this.start = start;
    }

    public List<HalApiDoc> getDocs() {
        return docs;
    }

    public void setDocs(List<HalApiDoc> docs) {
        this.docs = docs;
    }

    @Override
    public String toString() {
        return "HalApiResponseEntity{" +
                "numFound=" + numFound +
                ", start=" + start +
                ", docs=" + docs +
                '}';
    }
}
