package com.ciklum.tuitechtest.client.resource;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.ToString;

@Getter
@NoArgsConstructor
@AllArgsConstructor
@ToString
public class BranchResource {
    private String name;
    private CommitResource commit;
}
