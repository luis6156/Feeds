import { Component, OnInit } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ButtonModule } from 'primeng/button';
import { TextareaModule } from 'primeng/textarea';
import { FormsModule } from '@angular/forms';

interface Thread {
  id: string;
  user: User;
  content: string;
  media?: string;
  createdAt: string;
  likes: number;
  replies: Reply[];
}

interface Reply {
  id: string;
  user: User;
  content: string;
}

interface User {
  id: string;
  username: string;
  fullName: string;
  avatar: string;
  verified: boolean;
}

@Component({
  selector: 'app-home',
  standalone: true,
  imports: [
    CommonModule,
    ButtonModule,
    TextareaModule,
    FormsModule
  ],
  templateUrl: './home.component.html',
  styleUrls: ['./home.component.css']
})
export class HomeComponent implements OnInit {
  threads: Thread[] = [];
  suggestedUsers: User[] = [];
  newThreadContent: string = '';

  constructor() { }

  ngOnInit(): void {
    // Mock data
    this.threads = [
      {
        id: '1',
        user: {
          id: '1',
          username: 'zuck',
          fullName: 'Mark Zuckerberg',
          avatar: 'https://randomuser.me/api/portraits/men/83.jpg',
          verified: true
        },
        content: 'Threads is now on the web for everyone in the US! We\'re rolling out globally very soon.',
        media: 'https://picsum.photos/seed/thread1/600/400',
        createdAt: '3h',
        likes: 4382,
        replies: [
          {
            id: '101',
            user: {
              id: '2',
              username: 'jack',
              fullName: 'Jack Dorsey',
              avatar: 'https://randomuser.me/api/portraits/men/42.jpg',
              verified: true
            },
            content: 'Congrats on the launch!'
          }
        ]
      },
      {
        id: '2',
        user: {
          id: '3',
          username: 'janedoe',
          fullName: 'Jane Doe',
          avatar: 'https://randomuser.me/api/portraits/women/23.jpg',
          verified: false
        },
        content: 'Just tried the new Threads web app and it\'s so smooth! Love how fast it loads compared to other platforms. #threads #webapp',
        createdAt: '5h',
        likes: 128,
        replies: []
      },
      {
        id: '3',
        user: {
          id: '4',
          username: 'techcrunch',
          fullName: 'TechCrunch',
          avatar: 'https://randomuser.me/api/portraits/men/28.jpg',
          verified: true
        },
        content: 'Meta\'s Threads is finally available on web browsers, challenging X (formerly Twitter) on multiple fronts. The platform has already amassed over 100 million users since its mobile launch earlier this year.',
        media: 'https://picsum.photos/seed/thread3/600/400',
        createdAt: '7h',
        likes: 2157,
        replies: [
          {
            id: '102',
            user: {
              id: '5',
              username: 'sarahc',
              fullName: 'Sarah Chen',
              avatar: 'https://randomuser.me/api/portraits/women/45.jpg',
              verified: false
            },
            content: 'This is huge news for content creators!'
          },
          {
            id: '103',
            user: {
              id: '6',
              username: 'tomh',
              fullName: 'Tom Harris',
              avatar: 'https://randomuser.me/api/portraits/men/22.jpg',
              verified: false
            },
            content: 'Still missing some key features though...'
          }
        ]
      }
    ];

    this.suggestedUsers = [
      {
        id: '7',
        username: 'elonmusk',
        fullName: 'Elon Musk',
        avatar: 'https://randomuser.me/api/portraits/men/41.jpg',
        verified: true
      },
      {
        id: '8',
        username: 'natgeo',
        fullName: 'National Geographic',
        avatar: 'https://randomuser.me/api/portraits/women/65.jpg',
        verified: true
      },
      {
        id: '9',
        username: 'nasa',
        fullName: 'NASA',
        avatar: 'https://randomuser.me/api/portraits/men/57.jpg',
        verified: true
      }
    ];
  }

  createThread(): void {
    if (!this.newThreadContent.trim()) return;
    
    const newThread: Thread = {
      id: Date.now().toString(),
      user: {
        id: 'current-user',
        username: 'currentuser',
        fullName: 'Current User',
        avatar: 'https://randomuser.me/api/portraits/men/32.jpg',
        verified: false
      },
      content: this.newThreadContent,
      createdAt: 'now',
      likes: 0,
      replies: []
    };
    
    this.threads.unshift(newThread);
    this.newThreadContent = '';
  }
}