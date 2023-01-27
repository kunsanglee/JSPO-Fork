package com.jspo.member.dao;

import com.jspo.member.dto.MemberDto;
import org.apache.ibatis.annotations.Mapper;

@Mapper
public interface MemberDao {
    MemberDto insertMember(MemberDto memberDto) throws Exception;

    MemberDto selectMember(MemberDto memberDto) throws Exception;

    MemberDto login(MemberDto memberDto) throws Exception;
}
