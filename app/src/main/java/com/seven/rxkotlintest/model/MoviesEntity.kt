package com.seven.rxkotlintest.model


/**
 * Created by Seven on 2017/9/30.
 *
 */

data class Subject(
		var rating: Rating?,
		var genres: List<String?>?,
		var title: String?,
		var casts: List<Cast?>?,
		var collect_count: Int?,
		var original_title: String?,
		var subtype: String?,
		var directors: List<Director?>?,
		var year: String?,
		var images: Images?,
		var alt: String?,
		var id: String?
)

data class Rating(
		var max: Int?,
		var average: Double?,
		var stars: String?,
		var min: Int?
)

data class Cast(
		var alt: String?,
		var avatars: Avatars?,
		var name: String?,
		var id: String?
)

data class Avatars(
		var small: String?,
		var large: String?,
		var medium: String?
)

data class Director(
		var alt: String?,
		var avatars: Avatars?,
		var name: String?,
		var id: String?
)

data class Images(
		var small: String?,
		var large: String?,
		var medium: String?
)
