import kotlinx.datetime.LocalDate
import kotlinx.datetime.LocalDateTime
import ru.lazyhat.hackaton.data.models.Event
import ru.lazyhat.hackaton.data.models.EventStatus
import ru.lazyhat.hackaton.data.models.News

object Fake {
    val news = listOf(
        News(
            "News 1",
            "Content 1",
            "Author 1",
            LocalDateTime(2023, 5, 22, 20, 23, 34),
            "https://phonoteka.org/uploads/posts/2021-04/1619294996_44-phonoteka_org-p-vesti-fon-49.jpg",
            240U,
            false
        ),
        News(
            "News 2",
            "Content 2",
            "Author 2",
            LocalDateTime(2023, 3, 30, 10, 13, 14),
            null,
            223U,
            true
        )
    )

    val events = listOf(
        Event(
            EventStatus.Active,
            "Event 1",
            "Description 1",
            LocalDate(2023,11,23),
            "Location 1",
            "Organizer 1",
            "Content 1",
            "https://fikiwiki.com/uploads/posts/2022-02/1644969237_22-fikiwiki-com-p-krasivie-kartinki-zverei-29.jpg",
            LocalDate(2023,11,20),
            "A1,B1,C1,D1"
        ),
        Event(
            EventStatus.Archived,
            "Event 2",
            "Description 2",
            LocalDate(2023,10,30),
            "Location 2",
            "Organizer 2",
            "Content 2",
            null,
            LocalDate(2023,10,15),
            "A2,B2,C2,D2"
        ),
        Event(
            EventStatus.Registering,
            "Event 3",
            "Description 3",
            LocalDate(2023,12,13),
            "Location 3",
            "Organizer 3",
            "Content 3",
            null,
            LocalDate(2023,12,10),
            "A3,B3,C3,D3"
        )
    )
}