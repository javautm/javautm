package io.github.javautm.core.model

import io.github.javautm.core.model.UtmParameters.Companion.builder


/**
 * Immutable representation of the five standard UTM parameters.
 *
 * All fields are nullable. Use [builder] to construct instances.
 * The builder automatically normalizes every field.
 *
 * If a field is an empty strings it will return null.
 */
class UtmParameters private constructor(

    /**
     * Traffic source (e.g. google, chatgpt)
     */
    val source: String?,

    /**
     * Marketing medium (e.g. cpc, email)
     */
    val medium: String?,

    /**
     * Campaign name (e.g. spring_sale)
     */
    val campaign: String?,

    /**
     * Paid keyword (e.g. running+shoes)
     */
    val term: String?,

    /**
     * Content identifier used for A/B testing (e.g. logo link)
     */
    val content: String?
) {

    /**
     * @return true if at least one UTM parameter is present.
     */
    fun isPresent(): Boolean {
        return source != null
                || medium != null
                || campaign != null
                || term != null
                || content != null
    }

    /**
     * @return true when no UTM parameter is present.
     */
    fun isEmpty(): Boolean {
        return !isPresent()
    }

    override fun equals(other: Any?): Boolean {
        if (this === other) return true
        if (javaClass != other?.javaClass) return false

        other as UtmParameters

        if (source != other.source) return false
        if (medium != other.medium) return false
        if (campaign != other.campaign) return false
        if (term != other.term) return false
        if (content != other.content) return false

        return true
    }

    override fun hashCode(): Int {
        var result = source?.hashCode() ?: 0
        result = 31 * result + (medium?.hashCode() ?: 0)
        result = 31 * result + (campaign?.hashCode() ?: 0)
        result = 31 * result + (term?.hashCode() ?: 0)
        result = 31 * result + (content?.hashCode() ?: 0)
        return result
    }

    companion object {

        @JvmStatic
        fun builder() = Builder()

        /**
         * @return an instance with all fields null
         */
        @JvmStatic
        fun empty() = Builder().build()

        private fun normalize(value: String?): String? {
            return value
                ?.trim()
                ?.takeIf { it.isNotEmpty() }
        }

    }

    class Builder {

        private var source: String? = null
        private var medium: String? = null
        private var campaign: String? = null
        private var term: String? = null
        private var content: String? = null

        fun source(source: String?) = apply {
            this.source = normalize(source)
        }

        fun medium(medium: String?) = apply {
            this.medium = normalize(medium)
        }

        fun campaign(campaign: String?) = apply {
            this.campaign = normalize(campaign)
        }

        fun term(term: String?) = apply {
            this.term = normalize(term)
        }

        fun content(content: String?) = apply {
            this.content = normalize(content)
        }

        fun build(): UtmParameters {
            return UtmParameters(
                source = source,
                medium = medium,
                campaign = campaign,
                term = term,
                content = content
            )
        }

    }

}