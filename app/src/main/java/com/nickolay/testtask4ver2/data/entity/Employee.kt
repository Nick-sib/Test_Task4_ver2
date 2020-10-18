package com.nickolay.testtask4ver2.data.entity

data class Employee(
    val f_name: String,
    val l_name: String,
    val birthday: String?,
    val avatr_url: String?,
    val specialty: Array<Specialty>
) {
    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as Employee

        if (f_name != other.f_name) return false
        if (l_name != other.l_name) return false
        if (birthday != other.birthday) return false
        if (avatr_url != other.avatr_url) return false

        return true
    }

    override fun hashCode(): Int {
        var result = f_name.hashCode()
        result = 31 * result + l_name.hashCode()
        result = 31 * result + (birthday?.hashCode() ?: 0)
        result = 31 * result + (avatr_url?.hashCode() ?: 0)
        return result
    }
}

