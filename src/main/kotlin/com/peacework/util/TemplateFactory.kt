package com.peacework.util

import com.peacework.domain.controller.ConversationController
import com.peacework.domain.controller.UserController
import com.peacework.domain.model.User
import com.peacework.domain.ui.*
import kotlinx.serialization.json.Json

object TemplateFactory {
    suspend fun createLoungePage(user: User, conversationController: ConversationController): PageComponent {
        return PageComponent(
            title = "Lounge",
            components = listOf(
                Component(
                    type = ComponentType.WelcomeBanner.name,
                    data = hashMapOf(
                        "heading" to "Hello",
                        "subheading" to user.fullname.split(" ").first()
                    )
                ),
                Component(type = ComponentType.Divider.name),
                Component(
                    type = "ActionRow",
                    data = hashMapOf(
                        "text" to "Tap Actions to Expand & Filter Contacts",
                        "buttons" to Json.encodeToString(
                            ActionItemHolder.serializer(),
                            ActionItemHolder(
                                listOf(
                                    ActionComponent(
                                        id = "0",
                                        data = hashMapOf(
                                            "action" to "expand",
                                            "icon" to Json.encodeToString(
                                                IconPack.serializer(),
                                                IconPack(
                                                    icons = hashMapOf(
                                                        "iOS" to "slider.horizontal.below.rectangle"
                                                    )
                                                )
                                            )
                                        )
                                    ),
                                    ActionComponent(
                                        id = "1",
                                        data = hashMapOf(
                                            "action" to "filter",
                                            "icon" to Json.encodeToString(
                                                IconPack.serializer(),
                                                IconPack(
                                                    icons = hashMapOf(
                                                        "iOS" to "slider.horizontal.3"
                                                    )
                                                )
                                            )
                                        )
                                    ),
                                )
                            )
                        )
                    )
                ),
                Component(
                    type = ComponentType.HorizontalList.name,
                    data = hashMapOf(
                        "itemType" to "Contact",
                        "items" to Json.encodeToString(
                            ContactItemHolder.serializer(),
                            ContactItemHolder(user.contacts)
                        )
                    )
                ),
                Component(type = ComponentType.Divider.name),
                Component(
                    type = "SectionRow", data = hashMapOf(
                        "heading" to "Messages",
                        "subheading" to "Recents",
                        "action" to Json.encodeToString(
                            ActionComponent.serializer(), ActionComponent(
                                id = "0",
                                data = hashMapOf(
                                    "action" to "filter",
                                    "icon" to Json.encodeToString(
                                        IconPack.serializer(),
                                        IconPack(
                                            icons = hashMapOf(
                                                "iOS" to "slider.horizontal.3"
                                            )
                                        )
                                    )
                                )
                            )
                        )
                    )
                ),
                Component(type = ComponentType.Divider.name),
                Component(
                    type = "VerticalList",
                    data = hashMapOf(
                        "itemType" to "Conversation",
                        "items" to Json.encodeToString(
                            ConversationItemHolder.serializer(),
                            ConversationItemHolder(items = conversationController.getConversationsByUserID(user.userId))
                        )
                    )
                )
            )
        )
    }
}