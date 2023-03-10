import { ChangeDetectionStrategy, ChangeDetectorRef, Component, OnInit, ViewChild } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { UntilDestroy, untilDestroyed } from '@ngneat/until-destroy';
import { Chat, ChatMessage } from '../chat.component';
import { chatMessages } from '../../../../../static-data/chat-messages';
import { trackById } from '../../../../../@vex/utils/track-by';
import { chats } from '../../../../../static-data/chats';
import { map } from 'rxjs/operators';
import { fadeInUp400ms, } from '../../../../../@vex/animations/fade-in-up.animation';
import { UntypedFormControl, UntypedFormGroup } from '@angular/forms';
import { stagger20ms } from '../../../../../@vex/animations/stagger.animation';
import { ScrollbarComponent } from '../../../../../@vex/components/scrollbar/scrollbar.component';
import { ChatService } from '../chat.service';


@UntilDestroy()
@Component({
  selector: 'vex-chat-conversation',
  templateUrl: './chat-conversation.component.html',
  styleUrls: ['./chat-conversation.component.scss'],
  changeDetection: ChangeDetectionStrategy.OnPush,
  animations: [
    fadeInUp400ms,
    stagger20ms
  ]
})
export class ChatConversationComponent implements OnInit {

  chat: Chat;
  messages: ChatMessage[];

  form = new UntypedFormGroup({
    message: new UntypedFormControl()
  });

  trackById = trackById;

  @ViewChild(ScrollbarComponent, { static: true }) scrollbar: ScrollbarComponent;

  constructor(private route: ActivatedRoute,
              private chatService: ChatService,
              private cd: ChangeDetectorRef) { }

  ngOnInit() {
    this.route.paramMap.pipe(
      map(paramMap => +paramMap.get('chatId')),
      untilDestroyed(this)
    ).subscribe(chatId => {
      this.messages = [];
      this.cd.detectChanges();
      this.chat = chats.find(chat => chat.id === chatId);
      this.chat.unreadCount = 0;
      this.filterMessages(chatId);
      this.cd.detectChanges();

      this.scrollToBottom();
    });
  }

  filterMessages(id: ChatMessage['id']) {
    this.messages = chatMessages.filter(message => message.id === id);
  }

  send() {
    this.messages.push({
      id: this.chat.id,
      from: 'me',
      message: this.form.get('message').value
    });

    this.form.get('message').setValue('');

    this.cd.detectChanges();
    this.scrollToBottom();
  }

  scrollToBottom() {
    this.scrollbar.scrollbarRef.getScrollElement().scrollTo({
      behavior: 'smooth',
      top: this.scrollbar.scrollbarRef.getContentElement().clientHeight
    });
  }

  openDrawer() {
    this.chatService.drawerOpen.next(true);
    this.cd.markForCheck();
  }

  closeDrawer() {
    this.chatService.drawerOpen.next(false);
    this.cd.markForCheck();
  }
}
