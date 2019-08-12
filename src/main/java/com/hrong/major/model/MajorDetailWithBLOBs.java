package com.hrong.major.model;

public class MajorDetailWithBLOBs extends MajorDetail {
    private String description;

    private String jobProspect;

    private String course;

    private String ability;

    public String getDescription() {
        return description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public String getJobProspect() {
        return jobProspect;
    }

    public void setJobProspect(String jobProspect) {
        this.jobProspect = jobProspect;
    }

    public String getCourse() {
        return course;
    }

    public void setCourse(String course) {
        this.course = course;
    }

    public String getAbility() {
        return ability;
    }

    public void setAbility(String ability) {
        this.ability = ability;
    }
}