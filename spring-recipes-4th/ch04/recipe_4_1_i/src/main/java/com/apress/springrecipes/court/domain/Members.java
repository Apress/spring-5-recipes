package com.apress.springrecipes.court.domain;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;

import javax.xml.bind.annotation.XmlAccessType;
import javax.xml.bind.annotation.XmlAccessorType;
import javax.xml.bind.annotation.XmlElement;
import javax.xml.bind.annotation.XmlRootElement;

/**
 * Created by marten on 16-06-14.
 */
@XmlRootElement
@XmlAccessorType(XmlAccessType.FIELD)
public class Members {

    @XmlElement(name="member")
    private List<Member> members = new ArrayList<>();

    public List<Member> getMembers() {
        return members;
    }

    public void setMembers(List<Member> members) {
        this.members = members;
    }

    public void addMembers(Collection<Member> members) {
        this.members.addAll(members);
    }
}
